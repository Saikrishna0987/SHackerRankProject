package com.company.model;

public class Transaction {
    private String msg;
    private boolean result;
    private double costOftheOder;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public double getCostOftheOder() {
        return costOftheOder;
    }

    public void setCostOftheOder(double costOftheOder) {
        this.costOftheOder = costOftheOder;
    }
}
