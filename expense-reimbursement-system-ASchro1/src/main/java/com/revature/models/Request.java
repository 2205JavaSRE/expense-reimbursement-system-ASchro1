package com.revature.models;

public class Request {
    private int requestId;
    private String userId;
    private String type;
    private double amount;
    private String status;

    public Request(){

    }

    public Request(int requestId, String userId, String type, double amount, String status) {
        this.requestId = requestId;
        this.userId = userId;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
