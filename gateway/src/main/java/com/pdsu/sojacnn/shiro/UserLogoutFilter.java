package com.pdsu.sojacnn.shiro;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.utils.JsonUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author 半梦
 * @create 2020-12-15 13:12
 */
public class UserLogoutFilter extends LogoutFilter {

    private static final Logger log = LoggerFactory.getLogger("用户退出拦截器");

    private static final Result RESULT = Result.ok();

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        Subject subject = getSubject(request, response);
        try {
            subject.logout();
            String s = JsonUtils.valueOfString(RESULT);
            if(StringUtils.hasText(s)) {
                response.getOutputStream().write(s.getBytes());
            }
        } catch (Exception ise) {
            log.warn("用户退出登录失败", ise);
            return false;
        }
        return false;
    }

}
