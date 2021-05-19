package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author 半梦
 * @create 2021-05-19 16:45
 */
@RestControllerAdvice
@Log4j2
public class ExceptionController {

    @Value("${spring.application.name}")
    private String localhostName;

    @ExceptionHandler(Exception.class)
    public Result handingException(Exception e) {
        log.error("系统发生未知异常, 异常发生地址为: " + localhostName + ", 当前时间: " + DateUtils.nowDate(), e);
        return Result.fail();
    }

    /**
     * 处理 BindException 异常
     */
    @ExceptionHandler(BindException.class)
    public Result processBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMessage = "";
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMessage += error.getDefaultMessage() + " ";

        }
        log.warn("请求API时, 参数不符合规范, 原因: " + errorMessage, e);
        return Result.fail();
    }

    /**
     * 处理 MethodArgumentTypeMismatchException 异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result processMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.fail();
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result processMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.fail();
    }


}
