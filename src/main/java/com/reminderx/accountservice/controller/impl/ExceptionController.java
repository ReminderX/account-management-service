package com.reminderx.accountservice.controller.impl;

import com.reminderx.accountservice.controller.Controller;
import com.reminderx.accountservice.domain.ErrorResponse;
import com.reminderx.accountservice.helper.JsonMapper;

import javax.inject.Inject;

import static com.reminderx.accountservice.constant.HeaderConst.APPLICATION_JSON;
import static spark.Spark.*;

public class ExceptionController implements Controller {
    private final JsonMapper jsonMapper;

    @Inject
    public ExceptionController(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public void setupEndpoints() {
        notFound((request, response) -> {
            response.type(APPLICATION_JSON);
            return jsonMapper.serialize(new ErrorResponse("Route not found"));
        });

        internalServerError((request, response) -> {
            response.type(APPLICATION_JSON);
            return jsonMapper.serialize(new ErrorResponse("Internal server error"));
        });
    }
}
