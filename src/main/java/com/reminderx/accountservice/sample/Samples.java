package com.reminderx.accountservice.sample;

import com.google.common.collect.Sets;
import com.reminderx.accountservice.domain.Account;

import java.util.Set;

public class Samples {
    public final static Set<Account> ACCOUNT_SAMPLES = Sets.newHashSet(
            new Account("4377380078789898", 10000L),
            new Account("4377380078782323", 33000L));
}
