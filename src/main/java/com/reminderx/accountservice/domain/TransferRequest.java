package com.reminderx.accountservice.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransferRequest {
    @NotNull
    @NotBlank
    private final String from;

    @NotNull
    @NotBlank
    private final String to;

    @Min(0)
    private final long fund;

    public TransferRequest(String from, String to, long fund) {
        this.from = from;
        this.to = to;
        this.fund = fund;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public long getFund() {
        return fund;
    }
}
