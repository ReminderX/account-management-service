package com.reminderx.accountservice.exception;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Invalid request content");
    }
}
