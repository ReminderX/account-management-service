package com.reminderx.accountservice.exception;

public class NotEnoughFundsException extends RuntimeException implements CustomException {
    public NotEnoughFundsException(String number) {
        super(String.format("Not enough funds on %s account.", number));
    }
}
