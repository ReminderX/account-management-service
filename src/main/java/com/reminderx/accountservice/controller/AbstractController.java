package com.reminderx.accountservice.controller;

import com.reminderx.accountservice.Application;
import com.reminderx.accountservice.domain.ErrorResponse;
import com.reminderx.accountservice.domain.SuccessResponse;
import com.reminderx.accountservice.exception.CustomException;
import com.reminderx.accountservice.helper.JsonMapper;
import com.reminderx.accountservice.helper.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public abstract class AbstractController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private final JsonMapper jsonMapper;
    private final RequestValidator requestValidator;

    protected AbstractController(JsonMapper jsonMapper, RequestValidator requestValidator) {
        this.jsonMapper = jsonMapper;
        this.requestValidator = requestValidator;
    }

    protected <T> Object handleRequest(String body, Class<T> requestClass, Function<T, SuccessResponse> handler) {
        try {
            final T request = jsonMapper.deserialize(body, requestClass);
            requestValidator.validate(request);
            return handler.apply(request);
        } catch (Throwable t) {
            if (t instanceof CustomException) {
                logger.error(t.getMessage(), t);
                return new ErrorResponse(t.getMessage());
            } else {
                logger.error("Internal server error.", t);
                return new ErrorResponse("Internal server error.");
            }
        }
    }
}
