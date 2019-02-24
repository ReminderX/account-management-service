package com.reminderx.accountservice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.reminderx.accountservice.controller.JsonResponseTransformer;
import com.reminderx.accountservice.domain.Account;
import com.reminderx.accountservice.helper.JsonMapper;
import com.reminderx.accountservice.helper.RequestValidator;
import com.reminderx.accountservice.helper.impl.JsonMapperImpl;
import com.reminderx.accountservice.helper.impl.RequestValidatorImpl;
import com.reminderx.accountservice.repository.AccountRepository;
import com.reminderx.accountservice.repository.impl.AccountRepositoryImpl;
import com.reminderx.accountservice.sample.Samples;
import com.reminderx.accountservice.service.FundService;
import com.reminderx.accountservice.service.impl.FundServiceImpl;
import spark.ResponseTransformer;

import javax.inject.Singleton;
import javax.validation.Validation;
import javax.validation.Validator;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(JsonMapper.class).to(JsonMapperImpl.class).in(Singleton.class);
        bind(RequestValidator.class).to(RequestValidatorImpl.class).in(Singleton.class);
        bind(ResponseTransformer.class).to(JsonResponseTransformer.class).in(Singleton.class);
        bind(FundService.class).to(FundServiceImpl.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Provides
    @Singleton
    public AccountRepository accountRepository() {
        final AccountRepository accountRepository = new AccountRepositoryImpl();
        for (Account account : Samples.ACCOUNT_SAMPLES) {
            accountRepository.add(account);
        }
        return accountRepository;
    }
}
