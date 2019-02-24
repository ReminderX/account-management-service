package com.reminderx.accountservice.domain;

import static com.reminderx.accountservice.domain.ResponseStatus.ERROR;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class ErrorResponse {
    private final ResponseStatus status;
    private final ErrorData error;

    public ErrorResponse(String text) {
        this.status = ERROR;
        this.error = new ErrorData(text);
    }

    private class ErrorData {
        private final String text;

        private ErrorData(String text) {
            this.text = text;
        }
    }
}
