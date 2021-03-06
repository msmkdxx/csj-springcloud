package com.csj.cn.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReturnResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
}
