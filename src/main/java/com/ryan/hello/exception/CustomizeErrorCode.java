package com.ryan.hello.exception;

public enum CustomizeErrorCode implements ICoustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找到问题不在了，要不换个试试？"),

    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行恢复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYT_ERROR(2004,"服务冒烟了，要不然你稍等后再试试~~~"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空"),
    READ_NOTIFICATION_FAIL(2008,"兄弟你这读别人的信息呢！"),
    NOTIFICATION_NOT_FOUND(2009,"消息莫非是不翼而飞了！！？");



    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }




}