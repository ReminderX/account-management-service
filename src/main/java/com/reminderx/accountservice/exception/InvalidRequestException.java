package com.reminderx.accountservice.exception;

public class InvalidRequestException extends RuntimeException implements CustomException {
    public InvalidRequestException() {
        super("Invalid request content.");
    }
}
