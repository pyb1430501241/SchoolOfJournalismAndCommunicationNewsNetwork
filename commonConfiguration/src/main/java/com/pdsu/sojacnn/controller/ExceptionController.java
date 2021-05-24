package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.exception.account.PermissionsException;
import com.pdsu.sojacnn.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;

/**
 * @author 半梦
 * @create 2021-05-19 16:45
 */
@RestControllerAdvice
@Log4j2
public class ExceptionController implements AbstractController {

    @Value("${spring.application.name}")
    private String localhostName;

    @ExceptionHandler(Exception.class)
    public Result handingException(Exception e) {
        log.error("系统发生未知异常, 异常发生地址为: " + localhostName + ", 当前时间: " + DateUtils.nowDate(), e);
        return Result.fail().data(MESSAGE, DEFAULT_ERROR_PROMPT);
    }

    /**
     * 处理 IOException 异常
     */
    @ExceptionHandler(IOException.class)
    public Result processIOException(IOException e) {
        log.error("文件操作出现未知错误, 原因: " + e.getMessage());
        return Result.fail().data(MESSAGE, DEFAULT_ERROR_PROMPT);
    }

    /**
     * 处理 BindException 异常
     */
    @ExceptionHandler(BindException.class)
    public Result processBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMessage.append(error.getDefaultMessage()).append(" ");
        }
        log.warn("请求API时, 参数不符合规范, 原因: " + errorMessage, e);
        return Result.fail().data(MESSAGE, "参数不符合规范");
    }

    /**
     * 处理 MethodArgumentTypeMismatchException 异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result processMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.fail().data(MESSAGE, "请求方式错误");
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result processMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.fail().data(MESSAGE, "参数不对照");
    }

    /**
     * 处理 MissingServletRequestParameterException 异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result processHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.fail().data(MESSAGE, "请求方法不对应");
    }

    /**
     * 处理 PermissionsException 异常
     */
    @ExceptionHandler(PermissionsException.class)
    public Result processPermissionsException(PermissionsException e) {
        log.warn("请求API时发生未知错误, 原因: " + e.getMessage(), e);
        return Result.insufficientPermissions().data(MESSAGE, "权限不足");
    }


}
