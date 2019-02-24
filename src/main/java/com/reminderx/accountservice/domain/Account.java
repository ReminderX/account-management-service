package com.reminderx.accountservice.domain;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final String number;
    private double fund;

    public Account(String number, double fund) {
        this.number = number;
        this.fund = fund;
    }

    public String getNumber() {
        return number;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
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
