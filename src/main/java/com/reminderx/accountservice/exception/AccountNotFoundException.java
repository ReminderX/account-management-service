package com.reminderx.accountservice.exception;

public class AccountNotFoundException extends BusinessException {
    public AccountNotFoundException(String number) {
        super(String.format("Account %s was not found", number));
    }
}
