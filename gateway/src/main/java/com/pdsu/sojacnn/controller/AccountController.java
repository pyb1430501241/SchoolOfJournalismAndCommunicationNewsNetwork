package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.exception.account.AccountNotLoginException;
import com.pdsu.sojacnn.utils.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author 半梦
 * @create 2021-05-21 19:10
 */
@RestController
@Log4j2
@Api(description = "登录认证")
public class AccountController implements LoginController, AbstractController {

    @ApiOperation(value = "登录认证",response = Result.class)
    @PostMapping("/login")
    @Override
    public Result login(String account, String password, @RequestParam(defaultValue = "0") Integer remember) throws Exception{
        Subject subject = SecurityUtils.getSubject();

        // 如已登录，则退出当前登录用户
        if(Objects.nonNull(ShiroUtils.getNewsAccount())) {
            subject.logout();
        }

        // 生成登录密匙, 并判断是否记住
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        if(remember.equals(REMEMBER_ME)) {
            token.setRememberMe(true);
        }

        log.debug("开始登陆");
        /**
         * 登录, 实际调用为
         * @see com.pdsu.sojacnn.shiro.LoginRealm#doGetAuthenticationInfo(AuthenticationToken)
         */
        subject.login(token);

        // 登录成功则已保存用户数据
        NewsAccount newsAccount = (NewsAccount) subject.getPrincipal();

        // 从 json 中排除 password 字段
        newsAccount.setPassword(null);

        log.debug("用户 " + newsAccount.getId() + "登录成功");

        return Result.ok().data(DEFAULT_MESSAGE_NAME, newsAccount);
    }

    @ApiOperation(value = "获取用户登录状态, 需要携带请求头或者相应 Cookie")
    @GetMapping("/nav")
    @Override
    public Result loginStatus(HttpServletRequest request) throws Exception {
        NewsAccount newsAccount = ShiroUtils.getNewsAccount();

        loginOrNotLogin(newsAccount);

        return Result.ok().data(DEFAULT_MESSAGE_NAME, newsAccount);
    }

    @Override
    public void loginOrNotLogin(NewsAccount user) throws AccountNotLoginException {
        if(Objects.isNull(user)) {
            throw new AccountNotLoginException();
        }
    }
}
