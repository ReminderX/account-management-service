package com.reminderx.accountservice.exception;

public class AccountNotFoundException extends RuntimeException implements CustomException {
    public AccountNotFoundException(String number) {
        super(String.format("Account %s was not found.", number));
    }
}
