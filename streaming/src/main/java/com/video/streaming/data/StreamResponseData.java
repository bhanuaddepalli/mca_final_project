
package com.video.streaming.data;


import com.video.streaming.enums.StreamStatus;

/**
 * The Class StreamResponseData.
 */
public class StreamResponseData {
    /** The stream id. */
    private Long streamId;

    /** The stream name. */
    private String streamName;

    /** The stream application name. */
    private String streamApplicationName;

    /** The stream status. */
    private StreamStatus streamStatus;

    /** The stream url relative path. */
    private String streamUrlRelativePath;

    /** The stream rtsp url. */
    private String streamRtspUrl;
    
    /** The device id. */
    private Long deviceId;

    /** The device name. */
    private String deviceName;

    /** The comments. */
    private String comments;
    /**
     * Gets the stream id.
     *
     * @return the stream id
     */
    public Long getStreamId() {
        return streamId;
    }

    /**
     * Sets the stream id.
     *
     * @param streamId the new stream id
     */
    public void setStreamId(Long streamId) {
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

    /**
     * Gets the comments.
     *
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments.
     *
     * @param comments the new comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the device id.
     *
     * @return the device id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the device id.
     *
     * @param deviceId the new device id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets the device name.
     *
     * @return the device name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Sets the device name.
     *
     * @param deviceName the new device name
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

}
