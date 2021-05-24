package com.pdsu.sojacnn.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.exception.account.AccountAbnormalException;
import com.pdsu.sojacnn.service.NewsAccountRoleService;
import com.pdsu.sojacnn.service.NewsAccountService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

/**
 * @author 半梦
 * @create 2021-02-20 20:35
 */
@Log4j2
public class LoginRealm extends AuthorizingRealm {

    @Autowired
    private NewsAccountService newsAccountService;

    @Autowired
    private NewsAccountRoleService newsAccountRoleService;

    /**
     * 登录认证
     * 这里的 UserName 实际为
     * NewsAccount#getAccount()
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String account = upToken.getUsername();

        // 获取登录账号的详细信息
        NewsAccount newsAccount = getNewsAccount(account);

        // 查看其账号状态
        determineAccountStatus(newsAccount);

        // 因需要修改该对象信息, 且因为有缓存,
        // 故对其进行复制使用
        NewsAccount useAccount = newsAccount.copy();

        Object credentials = useAccount.getPassword();
        String realmName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(account);

        return new SimpleAuthenticationInfo(useAccount, credentials, credentialsSalt, realmName);
    }

    @Nullable
    private NewsAccount getNewsAccount(@NonNull String account) {
        QueryWrapper<NewsAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(NewsAccountService.ACCOUNT, account);
        try {
            return newsAccountService.getOne(queryWrapper, true);
        } catch (Exception e) {
            throw new AccountAbnormalException();
        }
    }

    /**
     * 如果没有对应用户, 直接抛出登录异常
     */
    @Contract("null -> fail")
    private void determineAccountStatus(NewsAccount account) {
        if(account == null) {
            throw new AccountAbnormalException();
        }
    }

    /**
     * 负责权限分配
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        NewsAccount account = (NewsAccount) principals.getPrimaryPrincipal();

        QueryWrapper<NewsAccountRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(NewsAccountRoleService.ACCOUNT_ID, account.getId());
        NewsAccountRole role = newsAccountRoleService.getOne(queryWrapper);

        // 设置权限
        account.setRole(role);

        return null;
    }

}
