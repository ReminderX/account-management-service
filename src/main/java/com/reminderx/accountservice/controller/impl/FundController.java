package com.reminderx.accountservice.controller.impl;

import com.reminderx.accountservice.controller.AbstractController;
import com.reminderx.accountservice.controller.Controller;
import com.reminderx.accountservice.controller.JsonResponseTransformer;
import com.reminderx.accountservice.domain.BalanceRequest;
import com.reminderx.accountservice.domain.DepositWithdrawRequest;
import com.reminderx.accountservice.domain.SuccessResponse;
import com.reminderx.accountservice.domain.TransferRequest;
import com.reminderx.accountservice.helper.JsonMapper;
import com.reminderx.accountservice.helper.RequestValidator;
import com.reminderx.accountservice.service.FundService;

import javax.inject.Inject;

import static com.reminderx.accountservice.constant.EndpointConst.API_V1;
import static com.reminderx.accountservice.constant.HeaderConst.APPLICATION_JSON;
import static com.reminderx.accountservice.constant.MethodNameConst.*;
import static spark.Spark.*;

public class FundController extends AbstractController implements Controller {
    private final JsonResponseTransformer jsonResponseTransformer;
    private final FundService fundService;

    @Inject
    public FundController(JsonMapper jsonMapper, RequestValidator requestValidator,
                          JsonResponseTransformer jsonResponseTransformer, FundService fundService) {
        super(jsonMapper, requestValidator);
        this.jsonResponseTransformer = jsonResponseTransformer;
        this.fundService = fundService;
    }

    @Override
    public void setupEndpoints() {
        post(API_V1 + BALANCE, APPLICATION_JSON,
                ((request, response) -> handleRequest(request, response, BalanceRequest.class, this::balanceHandler)),
                jsonResponseTransformer);

        post(API_V1 + DEPOSIT, APPLICATION_JSON,
                ((request, response) -> handleRequest(request, response, DepositWithdrawRequest.class, this::depositHandler)),
                jsonResponseTransformer);

        post(API_V1 + WITHDRAW, APPLICATION_JSON,
                ((request, response) -> handleRequest(request, response, DepositWithdrawRequest.class, this::withdrawHandler)),
                jsonResponseTransformer);

        post(API_V1 + TRANSFER, APPLICATION_JSON,
                ((request, response) -> handleRequest(request, response, TransferRequest.class, this::transferHandler)),
                jsonResponseTransformer);
    }

    private SuccessResponse balanceHandler(BalanceRequest request) {
        final long fund = fundService.balance(request.getNumber());
        return new SuccessResponse(fund);
    }

    private SuccessResponse depositHandler(DepositWithdrawRequest request) {
        final long fund = fundService.deposit(request.getNumber(), request.getFund());
        return new SuccessResponse(fund);
    }

    private SuccessResponse withdrawHandler(DepositWithdrawRequest request) {
        final long fund = fundService.withdraw(request.getNumber(), request.getFund());
        return new SuccessResponse(fund);
    }

    private SuccessResponse transferHandler(TransferRequest request) {
        fundService.transfer(request.getFrom(), request.getTo(), request.getFund());
        return new SuccessResponse();
    }
}
