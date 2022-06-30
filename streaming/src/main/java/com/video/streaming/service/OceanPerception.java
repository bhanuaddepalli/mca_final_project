
package com.video.streaming.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import com.video.streaming.constants.SysConstants;
import com.video.streaming.entity.Stream;
import com.video.streaming.enums.StreamStatus;
import com.video.streaming.repository.StreamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * The Class OceanPerception.
 */
@Component
public class OceanPerception {

    /** The Constant Log. */
    private static final Logger Log = LoggerFactory.getLogger(OceanPerception.class);

    /** The stream repository. */
    @Autowired
    private StreamRepository streamRepository;

    /** The rtmp port. */
    @Value("${op.offshore.ovm.rtmp.port}")
    private Integer rtmpPort;

    /** The logs folder. */
    @Value("${op.offshore.logs.folder.path}")
    private String logsFolder;

    /** The stream thread map. */
    private ConcurrentHashMap<Long, OpThread> streamThreadMap = new ConcurrentHashMap<>();

    /** The accepted status. */
    private static final HashSet<String> acceptedStatus = new HashSet<String>();
    static {
        acceptedStatus.add(StreamStatus.INACTIVE.toString());
        acceptedStatus.add(StreamStatus.EXCEPTION.toString());
    }

    /**
     * Gets the rtmp port.
     *
     * @return the rtmp port
     */
    public String getRtmpPort() {
        return Objects.isNull(rtmpPort) || rtmpPort <= 0 ? SysConstants.DEFAULT_RTMP_PORT : rtmpPort.toString();
    }

    /**
     * Gets the logs folder.
     *
     * @return the logs folder
     */
    public String getLogsFolder() {
        return logsFolder;
    }

    /**
     * On op closed event.
     */
    @PreDestroy()
    public void onOpClosedEvent() {
        List<Stream> streamList = streamRepository.findAllById(streamThreadMap.keySet());
        streamList.stream().forEach(stream -> {
            stream.setStreamStatus(StreamStatus.INACTIVE);
            stream.setComments("Server Stopped, Streams will  be UP after Restart");
        });
        streamRepository.saveAll(streamList);

    }

    /**
     * Do streaming frequently.
     *
     */
    @Scheduled(fixedDelayString = "60000")
    public void doStreamingFrequently() {
        List<Stream> streamDetailsList = streamRepository.findAll();
        streamDetailsList.forEach(streamDetails -> {

            if (! validateStreamDetails(streamDetails)) {
                streamDetails.setStreamStatus(StreamStatus.EXCEPTION);
                streamDetails.setComments("Stream Data was NULL / Not Acceptable");
                streamRepository.save(streamDetails);
            }
            if (streamThreadMap.containsKey(streamDetails.getStreamId())) {
                if (acceptedStatus.contains(streamDetails.getStreamStatus().toString()) ) {
                    if (Objects.nonNull(streamThreadMap.get(streamDetails.getStreamId()))) {
                        streamThreadMap.get(streamDetails.getStreamId()).stop();
                    }
                    streamThreadMap.remove(streamDetails.getStreamId());
                    OpThread opThread = new OpThread(streamDetails, this);
                    opThread.start();
                    streamThreadMap.put(streamDetails.getStreamId(), opThread);
                }
            }
            else {
                if (acceptedStatus.contains(streamDetails.getStreamStatus().toString()) ) {
                    OpThread opThread = new OpThread(streamDetails, this);
                    opThread.start();
                    streamThreadMap.put(streamDetails.getStreamId(), opThread);
                }
            }
        });

    }

    /**
     * Update streams.
     *
     * @param stream the stream
     */
    public void updateStreams(Stream stream) {
        streamRepository.save(stream);
    }

    /**
     * Update thread list.
     *
     * @param stream the stream
     */
    public void updateThreadList(Stream stream) {
        if (streamThreadMap.containsKey(stream.getStreamId())) {
            streamThreadMap.remove(stream.getStreamId());
        }
    }

    /**
     * Validate stream details.
     *
     * @param streamDetails the stream details
     * @return the boolean
     */
    private Boolean validateStreamDetails(Stream streamDetails) {
        if (Objects.isNull(streamDetails.getStreamName()) || Objects.isNull(streamDetails.getStreamApplicationName())
                || Objects.isNull(streamDetails.getStreamRtspUrl())) {
            return Boolean.FALSE;
        }
        if (streamDetails.getStreamName().equals("") || streamDetails.getStreamRtspUrl().equals("")
                || streamDetails.getStreamApplicationName().equals("")) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
