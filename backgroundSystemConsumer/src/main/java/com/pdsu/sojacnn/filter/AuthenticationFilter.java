package com.pdsu.sojacnn.filter;

import com.pdsu.sojacnn.controller.AbstractController;
import lombok.extern.log4j.Log4j2;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 半梦
 * @create 2021-05-24 8:32
 */
@Log4j2
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response
            , @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 如果请求头有用户凭证, 则进行请求转发
        String authorization = request.getHeader(AbstractController.ACCOUNT_SESSION_FLAG);
        if(StringUtils.hasText(authorization)) {
            log.debug("用户凭证为: " + authorization + ", 进行请求转发");
            filterChain.doFilter(request, response);
        } else {
            log.debug("无用户凭证, 拦截请求");
        }
    }

}
