package com.pdsu.sojacnn.controller;

import com.netflix.zuul.exception.ZuulException;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.exception.account.AccountAbnormalException;
import com.pdsu.sojacnn.exception.account.AccountNotLoginException;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 半梦
 * @create 2021-05-21 19:04
 */
@RestControllerAdvice
@Log4j2
public class ExceptionUpController extends ExceptionController {

    /**
     * 处理 AuthenticationException 异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result processAuthenticationException(AuthenticationException e) {
        if(e instanceof IncorrectCredentialsException) {
            log.info("用户登录时出现未知错误, 原因: 账号或密码错误");
            return Result.fail();
        } else if (e instanceof UnknownAccountException) {
            log.info("用户登录时出现未知错误, 原因: 账号不存在");
            return Result.fail();
        } else if (e instanceof AccountAbnormalException) {
            log.info("用户登录时出现未知错误, 原因: " + e.getMessage());
            return Result.fail();
        }
        log.info("用户登录时出现未知错误, 原因: " + e.getMessage());
        SecurityUtils.getSubject().logout();
        return Result.fail();
    }



    /**
     * 处理 UserNotLoginException 异常
     */
    @ExceptionHandler(AccountNotLoginException.class)
    public Result processUserNotLoginException(AccountNotLoginException e) {
        log.info("使用某些功能时出现未知错误, 原因: " + NOT_LOGIN);
        return Result.fail().data(MESSAGE, NOT_LOGIN);
    }


    @ExceptionHandler(ZuulException.class)
    public Result processZuulException(ZuulException e) {
        log.error("使用某些功能时出现未知错误, 原因: " + e.getMessage());
        return Result.fail().data(MESSAGE, DEFAULT_ERROR_PROMPT);
    }

}
