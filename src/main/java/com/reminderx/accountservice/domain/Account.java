package com.reminderx.accountservice.domain;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final String number;
    private long fund;

    public Account(String number, long fund) {
        this.number = number;
        this.fund = fund;
    }

    public String getNumber() {
        return number;
    }

    public long getFund() {
        return fund;
    }

    public void setFund(long fund) {
        this.fund = fund;
    }

    public void lockRead() {
        lock.readLock().lock();
    }

    public void lockWrite() {
        lock.writeLock().lock();
    }

    public void unlockRead() {
        lock.readLock().unlock();
    }

    public void unlockWrite() {
        lock.writeLock().unlock();
    }
}
