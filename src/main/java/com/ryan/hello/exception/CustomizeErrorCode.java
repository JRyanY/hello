package com.ryan.hello.exception;

public enum CustomizeErrorCode implements ICoustomizeErrorCode {
    QUESTION_NOT_FOUND("你找到问题不在了，要不换个试试？");



    public String getMessage() {
        return message;
    }
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }


}
