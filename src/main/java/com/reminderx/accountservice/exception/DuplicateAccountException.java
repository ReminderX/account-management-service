package com.reminderx.accountservice.exception;

public class DuplicateAccountException extends BusinessException {
    public DuplicateAccountException(String number) {
        super(String.format("Account %s is already exists", number));
    }
}
