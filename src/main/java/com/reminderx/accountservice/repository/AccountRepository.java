package com.reminderx.accountservice.repository;

import com.reminderx.accountservice.domain.Account;

public interface AccountRepository {
    void add(Account account);

    Account get(String number);
}
