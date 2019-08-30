package com.xy.community.exception;

/**
 * @Author:Kori
 * @Description:
 * @Date: Create in 18:17 2019/8/28
 * @Modified by:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND("问题不存在");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
