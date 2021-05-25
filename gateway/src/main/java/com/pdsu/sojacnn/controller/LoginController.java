package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录接口
 * @author 半梦
 * @create 2021-05-21 18:57
 */
public interface LoginController {

    Integer REMEMBER_ME = 1;

    /**
     * 登录
     * @param account 账号
     * @param password 密码
     * @param remember 是否记住我
     */
    Result login(String account, String password, Integer remember) throws Exception;

    /**
     * 用户认证状态
     * @return 已登录则返回用户数据
     */
    Result loginStatus(HttpServletRequest request) throws Exception;

}
