package com.video.streaming.data;

import java.util.Date;

public class APIExceptionResponse {

    /** The timestamp. */
    private Date timestamp;

    /** The status. */
    private Integer status;

    /** The error. */
    private String error;

    /** The message. */
    private String message;

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp the new timestamp
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new statu
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message.
     *
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Instantiates a new OPAPI exception response.
     *
     * @param timestamp the timestamp
     * @param status     the status
     * @param error     the error
     * @param message   the message
     */
    public APIExceptionResponse(Date timestamp, Integer status, String error, String message) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;

    }

}

