package com.csj.cn.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum  ErrorEnums implements IErrorCode {
    ERROR_PARAM("E_001","参数类型异常"),
    EMPTY_PARAM("E_002","参数为空异常"),
    ERROR_USER("E_003","用户名或密码错误"),
    ERROR_GOODS_LIKE("E_004","点赞异常");
    private String errorCode;
    private String errorMessage;

    @Override
    public String toString() {
        return "ErrorEnums{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
