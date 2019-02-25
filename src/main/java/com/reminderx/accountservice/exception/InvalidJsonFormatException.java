package com.reminderx.accountservice.exception;

public class InvalidJsonFormatException extends RuntimeException {
    public InvalidJsonFormatException(Exception e) {
        super("Invalid request format", e);
    }
}
