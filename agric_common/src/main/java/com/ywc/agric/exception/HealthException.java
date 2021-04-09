package com.ywc.agric.exception;

/**
 * @Author YWC
 * @Date 2021/3/28 9:13
 * 自定义异常
 */
public class HealthException extends RuntimeException {
    //message自定义异常名字
    public HealthException(String message) {
        //调用继承父类构造方法
        super(message);
    }
}
