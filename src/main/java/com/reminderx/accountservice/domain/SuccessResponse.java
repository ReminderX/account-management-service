package com.reminderx.accountservice.domain;

import static com.reminderx.accountservice.domain.ResponseStatus.OK;

@SuppressWarnings("unused")
public class SuccessResponse {
    private final ResponseStatus status;
    private final SuccessData data;

    public SuccessResponse() {
        this.status = OK;
        this.data = null;
    }

    public SuccessResponse(double fund) {
        this.status = OK;
        this.data = new SuccessData(fund);
    }

    private class SuccessData {
        private double fund;

        private SuccessData(double fund) {
            this.fund = fund;
        }
    }
}
