package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.exception.account.AccountNotLoginException;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.Nullable;

/**
 * @author 半梦
 * @create 2021-05-21 18:57
 */
public interface AuthenticationController {

    /**
     * 判断用户是否登录, 如未登录抛出异常
     */
    @Contract("null -> fail")
    void loginOrNotLogin(@Nullable NewsAccount user) throws AccountNotLoginException;

}
