package com.reminderx.accountservice.service.impl;

import com.reminderx.accountservice.domain.Account;
import com.reminderx.accountservice.exception.AccountNotFoundException;
import com.reminderx.accountservice.exception.NotEnoughFundsException;
import com.reminderx.accountservice.repository.AccountRepository;
import com.reminderx.accountservice.repository.impl.AccountRepositoryImpl;
import com.reminderx.accountservice.service.FundService;
import org.junit.Before;
import org.junit.Test;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class FundServiceImplTests {
    private final static String FIRST_ACCOUNT_NUMBER = "4377380078789898";
    private final static double FIRST_ACCOUNT_FUND = 10000.0;
    private final static String SECOND_ACCOUNT_NUMBER = "4377380078782323";
    private final static double SECOND_ACCOUNT_FUND = 33000.0;

    private final static String FIRST_UNKNOWN_ACCOUNT_NUMBER = "4377380055554444";
    private final static String SECOND_UNKNOWN_ACCOUNT_NUMBER = "4377380055557777";

    private FundService fundService;

    @Before
    public void setUp() {
        final AccountRepository accountRepository = new AccountRepositoryImpl();
        accountRepository.add(new Account(FIRST_ACCOUNT_NUMBER, FIRST_ACCOUNT_FUND));
        accountRepository.add(new Account(SECOND_ACCOUNT_NUMBER, SECOND_ACCOUNT_FUND));

        fundService = new FundServiceImpl(accountRepository);
    }

    // Correct balance test
    @Test
    public void getBalanceTest() {
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(FIRST_ACCOUNT_FUND)));
        assertThat(fundService.balance(SECOND_ACCOUNT_NUMBER), is(equalTo(SECOND_ACCOUNT_FUND)));
    }

    // Correct deposit test
    @Test
    public void doDepositTest() {
        assertThat(fundService.deposit(FIRST_ACCOUNT_NUMBER, 2000.0), is(equalTo(12000.0)));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(12000.0)));
    }

    // Correct withdraw test
    @Test
    public void doWithdrawTest() {
        assertThat(fundService.withdraw(FIRST_ACCOUNT_NUMBER, 2000.0), is(equalTo(8000.0)));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(8000.0)));
    }

    // Correct transfer test
    @Test
    public void doTransferTest() {
        fundService.transfer(FIRST_ACCOUNT_NUMBER, SECOND_ACCOUNT_NUMBER, 4000.0);
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(6000.0)));
        assertThat(fundService.balance(SECOND_ACCOUNT_NUMBER), is(equalTo(37000.0)));
    }

    // Failed balance of unknown account test
    @Test
    public void getBalanceOfUnknownAccountTest() {
        catchException(fundService).balance(FIRST_UNKNOWN_ACCOUNT_NUMBER);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
    }

    // Failed deposit to unknown account test
    @Test
    public void doDepositToUnknownAccountTest() {
        catchException(fundService).deposit(FIRST_UNKNOWN_ACCOUNT_NUMBER, 2000.0);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
    }

    // Failed withdraw from unknown account test
    @Test
    public void doWithdrawFromUnknownAccountTest() {
        catchException(fundService).withdraw(FIRST_UNKNOWN_ACCOUNT_NUMBER, 2000.0);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
    }

    // Failed too large withdraw from known account with balance check test
    @Test
    public void doTooLargeWithdrawTest() {
        catchException(fundService).withdraw(FIRST_ACCOUNT_NUMBER, 30000.0);
        assertThat(caughtException(), instanceOf(NotEnoughFundsException.class));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(FIRST_ACCOUNT_FUND)));
    }

    // Failed too large transfer between known accounts with balance check test
    @Test
    public void doTooLargeTransferTest() {
        catchException(fundService).transfer(FIRST_ACCOUNT_NUMBER, SECOND_ACCOUNT_NUMBER, 30000.0);
        assertThat(caughtException(), instanceOf(NotEnoughFundsException.class));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(FIRST_ACCOUNT_FUND)));
        assertThat(fundService.balance(SECOND_ACCOUNT_NUMBER), is(equalTo(SECOND_ACCOUNT_FUND)));
    }

    // Failed transfer from known account to unknown account with known account balance check test
    @Test
    public void doTransferToUnknownAccountTest() {
        catchException(fundService).transfer(FIRST_ACCOUNT_NUMBER, FIRST_UNKNOWN_ACCOUNT_NUMBER, 2000.0);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(FIRST_ACCOUNT_FUND)));
    }

    // Failed transfer from unknown account to known account with known account balance check test
    @Test
    public void doTransferFromUnknownAccountTest() {
        catchException(fundService).transfer(FIRST_UNKNOWN_ACCOUNT_NUMBER, FIRST_ACCOUNT_NUMBER, 2000.0);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
        assertThat(fundService.balance(FIRST_ACCOUNT_NUMBER), is(equalTo(FIRST_ACCOUNT_FUND)));
    }

    // Failed transfer between unknown accounts test
    @Test
    public void doTransferBetweenUnknownAccountsTest() {
        catchException(fundService).transfer(FIRST_UNKNOWN_ACCOUNT_NUMBER, SECOND_UNKNOWN_ACCOUNT_NUMBER, 2000.0);
        assertThat(caughtException(), instanceOf(AccountNotFoundException.class));
    }
}
