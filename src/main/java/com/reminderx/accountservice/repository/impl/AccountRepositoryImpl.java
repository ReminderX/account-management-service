package com.reminderx.accountservice.repository.impl;

import com.google.common.collect.Maps;
import com.reminderx.accountservice.domain.Account;
import com.reminderx.accountservice.exception.AccountNotFoundException;
import com.reminderx.accountservice.exception.DuplicateAccountException;
import com.reminderx.accountservice.repository.AccountRepository;

import java.util.concurrent.ConcurrentMap;

public class AccountRepositoryImpl implements AccountRepository {
    private final ConcurrentMap<String, Account> accounts = Maps.newConcurrentMap();

    @Override
    public void add(Account account) {
        final String number = account.getNumber();

        if (accounts.containsKey(number)) {
            throw new DuplicateAccountException(number);
        }

        accounts.put(number, account);
    }

    @Override
    public Account get(String number) {
        final Account account = accounts.get(number);
        if (account == null) {
            throw new AccountNotFoundException(number);
        }
        return account;
    }
}
