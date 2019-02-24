package com.reminderx.accountservice.helper.impl;

import com.reminderx.accountservice.exception.InvalidRequestException;
import com.reminderx.accountservice.helper.RequestValidator;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class RequestValidatorImpl implements RequestValidator {
    private final Validator validator;

    @Inject
    public RequestValidatorImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> void validate(T request) {
        final Set<ConstraintViolation<T>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            throw new InvalidRequestException();
        }
    }
}
