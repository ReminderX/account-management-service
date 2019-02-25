package com.reminderx.accountservice.service;

public interface FundService {
    long balance(String number);

    long deposit(String number, long fund);

    long withdraw(String number, long fund);

    void transfer(String from, String to, long fund);
}
