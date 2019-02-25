package com.reminderx.accountservice.repository.impl;

import com.reminderx.accountservice.domain.Account;
import com.reminderx.accountservice.exception.AccountNotFoundException;
import com.reminderx.accountservice.exception.DuplicateAccountException;
import com.reminderx.accountservice.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;

public class AccountRepositoryImplTests {
    private final static String FIRST_ACCOUNT_NUMBER = "4377380078789898";
    private final static long FIRST_ACCOUNT_FUND = 10000L;
    private final static String SECOND_ACCOUNT_NUMBER = "4377380078782323";
    private final static long SECOND_ACCOUNT_FUND = 33000L;

    private final static String UNKNOWN_ACCOUNT_NUMBER = "4377380055554444";

    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        accountRepository = new AccountRepositoryImpl();
        accountRepository.add(new Account(FIRST_ACCOUNT_NUMBER, FIRST_ACCOUNT_FUND));
        accountRepository.add(new Account(SECOND_ACCOUNT_NUMBER, SECOND_ACCOUNT_FUND));
    }

    // Adding duplicate account to repository test
    @Test(expected = DuplicateAccountException.class)
    public void addDuplicateTest() {
        accountRepository.add(new Account(FIRST_ACCOUNT_NUMBER, FIRST_ACCOUNT_FUND));
    }

    // Getting unknown account from repository test
    @Test(expected = AccountNotFoundException.class)
    public void getUnknownAccountTest() {
        accountRepository.get(UNKNOWN_ACCOUNT_NUMBER);
    }
}
