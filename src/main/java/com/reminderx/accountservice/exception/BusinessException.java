package com.reminderx.accountservice.exception;

public abstract class BusinessException extends RuntimeException {
    BusinessException(String message) {
        super(message);
    }
}
