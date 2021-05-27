package com.pdsu.sojacnn.aspect;

import com.mysql.cj.jdbc.Driver;
import com.pdsu.sojacnn.utils.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author:wl
 * @Date:2021/5/27 10:55
 * @projectName:sojacnn
 * @description:
 */
@Aspect
@Log4j2
@Configuration()
@ConditionalOnClass(Driver.class)
public class LoggingAspect {

    @Pointcut("execution(* com.pdsu.sojacnn.service.*.*(..))")
    public void logPointCut() { }

    @Before("logPointCut()")
    public void beforeServiceLog(JoinPoint joinPoint) {
        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        //类名
        String className = signature.getDeclaringType().getName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        //参数
        String args = JsonUtils.valueOfString(joinPoint.getArgs());
        log.info("即将执行方法为: {}.{}.{}", declaringTypeName, className, funcName
                + " 请求参数为: " + args);
    }

    @AfterReturning(pointcut = "logPointCut()", returning = "result")
    public void afterServiceLog(JoinPoint joinPoint, Object result) {
        Signature signature = joinPoint.getSignature();
        String declaringTypeName = signature.getDeclaringTypeName();
        String className = signature.getDeclaringType().getName();
        String funcName = signature.getName();
        log.info("执行方法: {}.{}.{} 成功", declaringTypeName, className, funcName);
        log.info("Result:" + result);
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String str = joinPoint.getTarget().getClass().getName() + "."
                + ((MethodSignature)joinPoint.getSignature()).getMethod().getName();
        log.error("执行方法 " + str + " 出现异常, 异常信息为: " + ex);
    }
}
