package com.reminderx.accountservice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.reminderx.accountservice.controller.Controller;
import com.reminderx.accountservice.controller.impl.FundController;
import com.reminderx.accountservice.module.BasicModule;
import com.reminderx.accountservice.properties.ServerProperties;

import static spark.Spark.ipAddress;
import static spark.Spark.port;

public class Application {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BasicModule());

        final ServerProperties serverProperties = injector.getInstance(ServerProperties.class);
        ipAddress(serverProperties.getIp());
        port(serverProperties.getPort());

        final Controller fundController = injector.getInstance(FundController.class);
        fundController.setupEndpoints();
    }
}
