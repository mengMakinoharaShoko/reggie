package com.reggie.common;

/**
 * 自定义业务异常类
 * @author YX200
 */
public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
