
package com.video.streaming.entity;


import com.video.streaming.enums.StreamStatus;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Class Stream.
 */
@Entity
@Table(name = "streams", schema = "OceanPerception")
public class Stream implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -1677991004806039396L;

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stream_id")
    private long streamId;

    /** The stream name. */
    @Column(name = "stream_name", columnDefinition = "varchar(45)")
    private String streamName;

    /** The stream application name. */
    @Column(name = "stream_output_url")
    private String streamApplicationName;

    /** The stream status. */
    @Column(name = "stream_status", columnDefinition = "enum( 'ACTIVE','INACTIVE','EXCEPTION','TERMINATED') default 'INACTIVE'")
    @Enumerated(EnumType.STRING)
    private StreamStatus streamStatus;

    /** The stream url relative path. */
    @Column(name = "stream_url_relative_path")
    private String streamUrlRelativePath;

    /** The stream rtsp url. */
    @Column(name = "stream_rtsp_url")
    private String streamRtspUrl;

    /** The comments. */
    @Column(name = "stream_run_comments")
    private String comments;

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
     * Gets the device definition.
     *
     * @return the device definition
     */

}
