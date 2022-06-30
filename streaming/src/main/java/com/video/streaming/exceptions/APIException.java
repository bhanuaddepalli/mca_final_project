package com.video.streaming.exceptions;

import org.springframework.http.HttpStatus;

public class APIException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7292396996678999851L;

    /** The status. */
    private HttpStatus status;

    /** The message. */
    private String message;

    /**
     * Instantiates a new OPAPI exception.
     *
     * @param status  the status
     * @param message the message
     */
    public APIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Instantiates a new OPAPI exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * Gets the message.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return message;
    }
}