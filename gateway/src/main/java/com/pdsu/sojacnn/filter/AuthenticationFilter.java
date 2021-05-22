package com.pdsu.sojacnn.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.bean.ZuulStatus;
import com.pdsu.sojacnn.controller.AbstractController;
import com.pdsu.sojacnn.utils.HttpUtils;
import com.pdsu.sojacnn.utils.JsonUtils;
import com.pdsu.sojacnn.utils.ShiroUtils;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 对请求进行鉴权
 * @author 半梦
 * @create 2021-05-21 19:35
 */
@Component
@Log4j2(topic = "debug")
public class AuthenticationFilter extends ZuulFilter {

    private static final String BEFORE_REQUEST = ZuulStatus.PRE.getStatus();

    private static final String ACCOUNT_SESSION_FLAG = AbstractController.ACCOUNT_SESSION_FLAG;

    @Override
    public String filterType() {
        return BEFORE_REQUEST;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        context.setSendZuulResponse(true);
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        String servletPath = request.getServletPath();

        String referer = request.getHeader("Referer");

        if(!StringUtils.hasText(referer)) {
            // 如没有 referer 请求头, 则置为空字符串, 避免空指针异常
            referer = "";
        }

        // 放行 swagger 请求
        if(servletPath.contains("v2/api-docs") || referer.contains("swagger-ui")) {
            log.debug("放行 swagger 请求");
            return null;
        }

        // 如请求管理员且未登录则拦截请求
        if(servletPath.contains("background")) {
            // 如果用户没有认证
            if(!ShiroUtils.isAuthorization(request)) {
                log.debug("未携带相应用户认证信息");
                return filterAuthorization(context);
            }

            // 是否可以获取到用户信息
            NewsAccount account = ShiroUtils.getNewsAccount();
            if(Objects.isNull(account)) {
                log.debug("拦截未登录请求");
                return filterAuthorization(context);
            }
            context.addZuulRequestHeader(ACCOUNT_SESSION_FLAG, account.getId().toString());
        }

        log.info("用户IP:" + HttpUtils.getIpAddr(request) + ", 访问URL: " + servletPath);
        // 放行剩余请求
        return null;
    }

    /**
     * 请求权限不足时执行
     */
    @Nullable
    private Object filterAuthorization(@NonNull RequestContext context) {
        context.setSendZuulResponse(false);
        context.setResponseBody(JsonUtils.valueOfString((Result.insufficientPermissions())));
        return null;
    }

}
