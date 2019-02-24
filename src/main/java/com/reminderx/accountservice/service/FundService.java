package com.reminderx.accountservice.service;

public interface FundService {
    double balance(String number);
    double deposit(String number, double fund);
    double withdraw(String number, double fund);
    void transfer(String from, String to, double fund);
}
