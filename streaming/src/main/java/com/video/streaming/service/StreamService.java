package com.video.streaming.service;

import com.video.streaming.data.StreamRequestData;
import com.video.streaming.data.StreamResponseData;
import com.video.streaming.exceptions.APIException;
import com.video.streaming.repository.StreamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.video.streaming.entity.Stream;

@Service
public class StreamService {
    /** The stream repository. */
    @Autowired
    StreamRepository streamRepository;

    /** The mapper. */
    @Autowired
    ModelMapper mapper;


    /**
     * Gets the all streams.
     *
     * @return the all streams
     */
    
    public List<StreamResponseData> getAllStreams() {

        return mapToResponseListData(streamRepository.findAll());
    }

    /**
     * Gets the streams by id.
     *
     * @param id the id
     * @return the streams by id
     */
    
    public StreamResponseData getStreamsById(long id) {
        return mapToResponseData(streamRepository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Stream with given id not found.")));
    }

    /**
     * Creates the stream.
     *
     * @param streamRequestData the stream request data
     * @return the stream
     */
    
    public StreamResponseData createStream(StreamRequestData streamRequestData) {
        Stream streamDB = mapToEntity(streamRequestData);
        Stream newStream = streamRepository.save(streamDB);
        return mapToResponseData(newStream);
    }

    /**
     * Update stream.
     *
     * @param streamRequestData the stream request data
     * @param id                the id
     * @return the stream
     */
    
    public StreamResponseData updateStream(StreamRequestData streamRequestData, long id) {
        Stream streamDb = streamRepository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Stream details not found."));
        streamRequestData.setStreamId(id);
        streamDb = mapToEntity(streamRequestData);
        return mapToResponseData(streamRepository.save(streamDb));

    }

    /**
     * Delete stream.
     *
     * @param id the id
     */
    
    public void deleteStream(long id) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new APIException(HttpStatus.NOT_FOUND, "Stream id not found."));
        streamRepository.delete(stream);

    }

    /**
     * Map to entity.
     *
     * @param streamRequestData the stream request data
     * @return the project
     */
    private Stream mapToEntity(StreamRequestData streamRequestData) {
        Stream stream = new Stream();
        stream.setStreamId(streamRequestData.getStreamId());
        stream.setStreamApplicationName(streamRequestData.getStreamApplicationName());
        stream.setStreamName(streamRequestData.getStreamName());
        stream.setStreamRtspUrl(streamRequestData.getStreamRtspUrl());
        stream.setStreamStatus(streamRequestData.getStreamStatus());
        stream.setStreamUrlRelativePath(streamRequestData.getStreamUrlRelativePath());
        return stream;
    }


    /**
     * Map to response list data.
     *
     * @param stream the stream
     * @return the list
     */
    private List<StreamResponseData> mapToResponseListData(List<Stream> stream) {
        List<StreamResponseData> streamResponseData = new ArrayList<>();
        for (Stream data : stream) {
            streamResponseData.add(mapToResponseData(data));
        }
        return streamResponseData;
    }

    /**
     * Map to response data.
     *
     * @param stream the stream
     * @return the stream response data
     */
    private StreamResponseData mapToResponseData(Stream stream) {
        StreamResponseData sData = new StreamResponseData();
        sData = mapper.map(stream, StreamResponseData.class);
        sData.setComments(stream.getComments());
        return sData;
    }

}
