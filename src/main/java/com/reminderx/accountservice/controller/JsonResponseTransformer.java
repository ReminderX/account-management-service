package com.reminderx.accountservice.controller;

import com.reminderx.accountservice.helper.JsonMapper;
import spark.ResponseTransformer;

import javax.inject.Inject;

public class JsonResponseTransformer implements ResponseTransformer {
    private final JsonMapper jsonMapper;

    @Inject
    public JsonResponseTransformer(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public String render(Object model) {
        return jsonMapper.serialize(model);
    }
}
