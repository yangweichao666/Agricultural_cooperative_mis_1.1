package com.ywc.agric.controller;

import com.ywc.agric.entity.Result;
import com.ywc.agric.exception.HealthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 定义全局异常处理
 * @Author YWC
 * @Date 2021/3/28 10:24
 */
@RestControllerAdvice
public class HealExceptionAdvice {
    /**
     *  info:  打印日志，记录流程性的内容
     *  debug: 记录一些重要的数据 id, orderId, userId
     *  error: 记录异常的堆栈信息，代替e.printStackTrace();
     *  工作中不能有System.out.println(), e.printStackTrace();
     */
    //日志对象
    private static final Logger log = LoggerFactory.getLogger(HealthException.class);
    @ExceptionHandler(HealthException.class)
    public Result handleHealthException(HealthException e){
        return new Result(false, e.getMessage());
    }
    //异常范围是由小到大
    @ExceptionHandler (Exception.class)
    public Result handleException(Exception e){
        log.error("发生异常");
        return new Result (false,"未知错误，操作异常，请联系管理员");
    }
    /**
     * 密码错误
     * @param he
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Result handBadCredentialsException(BadCredentialsException he){
        return handleUserPassword();
    }

    /**
     * 用户名不存在
     * @param he
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Result handInternalAuthenticationServiceException(InternalAuthenticationServiceException he){
        return handleUserPassword();
    }

    private Result handleUserPassword(){
        return new Result(false, "用户名或密码错误");
    }

    /**
     * 权限不足
     * @param he
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result handAccessDeniedException(AccessDeniedException he){
        return new Result(false, "没有权限");
    }

}
