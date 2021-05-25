package com.pdsu.sojacnn.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.bean.ZuulStatus;
import com.pdsu.sojacnn.utils.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author 半梦
 * @create 2021-05-25 8:23
 */
@Component
@Log4j2
public class ErrorFilter extends ZuulFilter {

    private static final String ERROR_REQUEST = ZuulStatus.ERROR.getStatus();

    @Override
    public String filterType() {
        return ERROR_REQUEST;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.warn("zuul 转发发生未知错误...");
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.setResponseBody(JsonUtils.valueOfString(Result.fail().data("message", "请求超时")));
        return null;
    }

}
