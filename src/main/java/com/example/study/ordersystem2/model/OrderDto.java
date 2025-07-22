package com.example.study.ordersystem2.model;

public class OrderDto {
    private String type;
    private String userEmail;
    private int amount;
    private boolean vip;

    public OrderDto() {}

    public OrderDto(String type, String userEmail, int amount, boolean vip) {
        this.type = type;
        this.userEmail = userEmail;
        this.amount = amount;
        this.vip = vip;
    }

    public String getType() {
        return type;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isVip() {
        return vip;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}