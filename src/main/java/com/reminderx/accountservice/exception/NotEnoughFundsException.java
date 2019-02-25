package com.reminderx.accountservice.exception;

public class NotEnoughFundsException extends BusinessException {
    public NotEnoughFundsException(String number) {
        super(String.format("Not enough funds on %s account", number));
    }
}
