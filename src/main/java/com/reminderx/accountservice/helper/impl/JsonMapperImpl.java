package com.reminderx.accountservice.helper.impl;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.reminderx.accountservice.exception.InvalidJsonFormatException;
import com.reminderx.accountservice.helper.JsonMapper;

public class JsonMapperImpl implements JsonMapper {
    private final Gson gson = new Gson();

    @Override
    public String serialize(Object source) {
        return gson.toJson(source);
    }

    @Override
    public <T> T deserialize(String source, Class<T> clazz) {
        try {
            return gson.fromJson(source, clazz);
        } catch (JsonParseException e) {
            throw new InvalidJsonFormatException(e);
        }
    }
}
