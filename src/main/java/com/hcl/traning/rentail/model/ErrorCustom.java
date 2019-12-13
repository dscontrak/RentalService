package com.hcl.traning.rentail.model;

public class ErrorCustom {

    private int code;
    private String message;

    public ErrorCustom() {
    }

    public ErrorCustom(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}