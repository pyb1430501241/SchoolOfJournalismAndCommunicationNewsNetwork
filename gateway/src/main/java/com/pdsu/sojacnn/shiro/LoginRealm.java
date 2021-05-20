package com.pdsu.sojacnn.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.exception.account.AccountAbnormalException;
import com.pdsu.sojacnn.service.NewsAccountService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

/**
 * @author 半梦
 * @create 2021-02-20 20:35
 */
@Log4j2
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private NewsAccountService newsAccountService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
        String userName = uptoken.getUsername();

        // 获取登录账号的详细信息
        NewsAccount user = getNewsAccount(userName);

        // 查看其账号状态
        determineAccountStatus(user);

        Object credentials = user.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        return new SimpleAuthenticationInfo(user, credentials, credentialsSalt, realmName);
    }

    private NewsAccount getNewsAccount(@NonNull String userName) {
        QueryWrapper<NewsAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        try {
            return newsAccountService.getOne(queryWrapper, true);
        } catch (Exception e) {
            throw new AccountAbnormalException();
        }
    }

    private void determineAccountStatus(NewsAccount account) {
        if(account == null) {
            throw new AccountAbnormalException();
        }
    }

    /**
     * 负责权限分配
     * 舍弃
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}
