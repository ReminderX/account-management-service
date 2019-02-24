package com.reminderx.accountservice.helper;

public interface RequestValidator {
    <T> void validate(T request);
}
