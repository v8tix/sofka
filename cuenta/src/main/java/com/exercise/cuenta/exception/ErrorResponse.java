package com.exercise.cuenta.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public ErrorResponse(HttpStatus status, String message) {
        this(status.value(), status.getReasonPhrase(), message);
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}