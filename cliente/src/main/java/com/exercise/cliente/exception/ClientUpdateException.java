package com.exercise.cliente.exception;

public class ClientUpdateException extends RuntimeException {
    public ClientUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}