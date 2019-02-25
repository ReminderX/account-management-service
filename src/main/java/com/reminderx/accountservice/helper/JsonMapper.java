package com.reminderx.accountservice.helper;

public interface JsonMapper {
    <T> String serialize(T source);

    <T> T deserialize(String source, Class<T> clazz);
}
