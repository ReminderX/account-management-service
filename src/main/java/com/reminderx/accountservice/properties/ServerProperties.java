package com.reminderx.accountservice.properties;

@SuppressWarnings("unused")
public class ServerProperties {
    private String ip = "0.0.0.0";
    private int port = 8080;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
