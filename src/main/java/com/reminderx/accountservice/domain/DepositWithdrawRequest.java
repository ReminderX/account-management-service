package com.reminderx.accountservice.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DepositWithdrawRequest {
    @NotNull
    @NotBlank
    private final String number;

    @Min(0)
    private final double fund;

    public DepositWithdrawRequest(String number, double fund) {
        this.number = number;
        this.fund = fund;
    }

    public String getNumber() {
        return number;
    }

    public double getFund() {
        return fund;
    }
}
