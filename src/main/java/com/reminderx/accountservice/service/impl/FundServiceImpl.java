package com.reminderx.accountservice.service.impl;

import com.reminderx.accountservice.domain.Account;
import com.reminderx.accountservice.exception.NotEnoughFundsException;
import com.reminderx.accountservice.repository.AccountRepository;
import com.reminderx.accountservice.service.FundService;

import javax.inject.Inject;

public class FundServiceImpl implements FundService {
    private final AccountRepository accountRepository;

    @Inject
    public FundServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public long balance(String number) {
        final Account account = accountRepository.get(number);
        account.lockRead();
        try {
            return account.getFund();
        } finally {
            account.unlockRead();
        }
    }

    @Override
    public long deposit(String number, long fund) {
        final Account account = accountRepository.get(number);
        account.lockWrite();
        try {
            final long newFund = account.getFund() + fund;
            account.setFund(newFund);
            return newFund;
        } finally {
            account.unlockWrite();
        }
    }

    @Override
    public long withdraw(String number, long fund) {
        final Account account = accountRepository.get(number);
        account.lockWrite();
        try {
            if (account.getFund() < fund) {
                throw new NotEnoughFundsException(number);
            }

            final long newFund = account.getFund() - fund;
            account.setFund(newFund);
            return newFund;
        } finally {
            account.unlockWrite();
        }
    }

    @Override
    public void transfer(String from, String to, long fund) {
        if (!from.equals(to)) {
            final Account sourceAccount = accountRepository.get(from);
            final Account targetAccount = accountRepository.get(to);

            sourceAccount.lockWrite();
            targetAccount.lockWrite();
            try {
                if (sourceAccount.getFund() < fund) {
                    throw new NotEnoughFundsException(from);
                }

                sourceAccount.setFund(sourceAccount.getFund() - fund);
                targetAccount.setFund(targetAccount.getFund() + fund);
            } finally {
                sourceAccount.unlockWrite();
                targetAccount.unlockWrite();
            }
        }
    }
}
