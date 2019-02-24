package com.reminderx.accountservice.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceRequest {
    @NotNull
    @NotBlank
    private final String number;

    public BalanceRequest(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
