package com.reminderx.accountservice.controller;

import com.reminderx.accountservice.Application;
import com.reminderx.accountservice.domain.ErrorResponse;
import com.reminderx.accountservice.domain.SuccessResponse;
import com.reminderx.accountservice.exception.BusinessException;
import com.reminderx.accountservice.exception.InvalidJsonFormatException;
import com.reminderx.accountservice.exception.InvalidRequestException;
import com.reminderx.accountservice.helper.JsonMapper;
import com.reminderx.accountservice.helper.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.function.Function;

import static com.reminderx.accountservice.constant.HeaderConst.APPLICATION_JSON;
import static com.reminderx.accountservice.constant.HttpStatusConst.BAD_REQUEST;
import static spark.Spark.halt;

public abstract class AbstractController {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private final JsonMapper jsonMapper;
    private final RequestValidator requestValidator;

    protected AbstractController(JsonMapper jsonMapper, RequestValidator requestValidator) {
        this.jsonMapper = jsonMapper;
        this.requestValidator = requestValidator;
    }

    protected <T> Object handleRequest(Request request, Response response, Class<T> requestClass,
                                       Function<T, SuccessResponse> handler) {
        try {
            response.type(APPLICATION_JSON);

            final T requestBody = jsonMapper.deserialize(request.body(), requestClass);
            requestValidator.validate(requestBody);

            return handler.apply(requestBody);
        } catch (BusinessException e) {
            logger.error(e.getMessage(), e);
            return new ErrorResponse(e.getMessage());
        } catch (InvalidRequestException | InvalidJsonFormatException e) {
            logger.error(e.getMessage(), e);
            halt(BAD_REQUEST, jsonMapper.serialize(new ErrorResponse(e.getMessage())));
            return null;
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            throw t;
        }
    }
}
