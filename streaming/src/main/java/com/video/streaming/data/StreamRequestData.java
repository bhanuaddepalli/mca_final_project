
package com.video.streaming.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.video.streaming.enums.StreamStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The Class StreamRequestData.
 */
public class StreamRequestData {

    /** The stream id. */
    @JsonIgnore
    private long streamId;

    /** The stream name. */
    @NotNull
    @NotBlank(message = "Stream Name should not Empty")
    private String streamName;

    /** The stream application name. */
    @NotNull
    @NotBlank(message = "application Name should not Empty")
    private String streamApplicationName;

    /** The stream status. */
    private StreamStatus streamStatus;

    /** The stream url relative path. */
    private String streamUrlRelativePath;

    /** The stream rtsp url. */
    @NotNull
    @NotBlank(message = "RTSP Url should not Empty")
    private String streamRtspUrl;
    

    /**
     * Gets the stream id.
     *
     * @return the stream id
     */
    public long getStreamId() {
        return streamId;
    }

    /**
     * Sets the stream id.
     *
     * @param streamId the new stream id
     */
    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    /**
     * Gets the stream name.
     *
     * @return the stream name
     */
    public String getStreamName() {
        return streamName;
    }

    /**
     * Sets the stream name.
     *
     * @param streamName the new stream name
     */
    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    /**
     * Gets the stream application name.
     *
     * @return the stream application name
     */
    public String getStreamApplicationName() {
        return streamApplicationName;
    }

    /**
     * Sets the stream application name.
     *
     * @param streamApplicationName the new stream application name
     */
    public void setStreamApplicationName(String streamApplicationName) {
        this.streamApplicationName = streamApplicationName;
    }

    /**
     * Gets the stream status.
     *
     * @return the stream status
     */
    public StreamStatus getStreamStatus() {
        return streamStatus;
    }

    /**
     * Sets the stream status.
     *
     * @param streamStatus the new stream status
     */
    public void setStreamStatus(StreamStatus streamStatus) {
        this.streamStatus = streamStatus;
    }

    /**
     * Gets the stream url relative path.
     *
     * @return the stream url relative path
     */
    public String getStreamUrlRelativePath() {
        return streamUrlRelativePath;
    }

    /**
     * Sets the stream url relative path.
     *
     * @param streamUrlRelativePath the new stream url relative path
     */
    public void setStreamUrlRelativePath(String streamUrlRelativePath) {
        this.streamUrlRelativePath = streamUrlRelativePath;
    }

    /**
     * Gets the stream rtsp url.
     *
     * @return the stream rtsp url
     */
    public String getStreamRtspUrl() {
        return streamRtspUrl;
    }

    /**
     * Sets the stream rtsp url.
     *
     * @param streamRtspUrl the new stream rtsp url
     */
    public void setStreamRtspUrl(String streamRtspUrl) {
        this.streamRtspUrl = streamRtspUrl;
    }


}
