package com.reminderx.accountservice.exception;

public class InvalidJsonFormatException extends RuntimeException implements CustomException {
    public InvalidJsonFormatException(Exception e) {
        super("Invalid request format.", e);
    }
}
