package com.xy.community.exception;

/**
 * @Author:Kori
 * @Description:
 * @Date: Create in 18:17 2019/8/28
 * @Modified by:
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"问题不存在"),
    TARGET_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"请先登录"),
    SYSTEM_ERROR(2004,"系统错误"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在"),
    CCOMMENT_NOT_FOUND(2006,"评论不存在" );

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
