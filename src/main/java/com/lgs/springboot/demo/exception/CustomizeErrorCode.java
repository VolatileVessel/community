package com.lgs.springboot.demo.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND("你找的那个问题不在了，要不要换个试试？");

    @Override
    public String getMessage() {
        return null;
    }
    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
