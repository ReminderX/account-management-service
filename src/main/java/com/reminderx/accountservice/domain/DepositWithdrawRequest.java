package com.reminderx.accountservice.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepositWithdrawRequest {
    @NotNull
    @NotBlank
    private final String number;

    @Min(0)
    private final long fund;

    public DepositWithdrawRequest(String number, long fund) {
        this.number = number;
        this.fund = fund;
    }

    public String getNumber() {
        return number;
    }

    public long getFund() {
        return fund;
    }
}
