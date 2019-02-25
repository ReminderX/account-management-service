package com.reminderx.accountservice.controller.impl;

import com.reminderx.accountservice.controller.Controller;
import com.reminderx.accountservice.controller.JsonResponseTransformer;
import com.reminderx.accountservice.domain.SuccessResponse;

import javax.inject.Inject;

import static com.reminderx.accountservice.constant.EndpointConst.API_V1;
import static com.reminderx.accountservice.constant.HeaderConst.APPLICATION_JSON;
import static com.reminderx.accountservice.constant.MethodNameConst.HEALTH;
import static spark.Spark.get;

public class ServiceController implements Controller {
    private final JsonResponseTransformer jsonResponseTransformer;

    @Inject
    public ServiceController(JsonResponseTransformer jsonResponseTransformer) {
        this.jsonResponseTransformer = jsonResponseTransformer;
    }

    @Override
    public void setupEndpoints() {
        get(API_V1 + HEALTH, APPLICATION_JSON, (request, response) -> {
            response.type(APPLICATION_JSON);
            return new SuccessResponse();
        }, jsonResponseTransformer);
    }
}
