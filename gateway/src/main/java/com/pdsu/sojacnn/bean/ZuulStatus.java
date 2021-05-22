package com.pdsu.sojacnn.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @create 2021-05-21 19:36
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ZuulStatus {

    /**
     * 请求转发之前
     */
    PRE("pre"),
    /**
     * 请求转发之后
     */
    POST("post"),
    /**
     * 路由请求转发
     */
    ROTE("rote"),
    /**
     * 错误时执行
     */
    ERROR("error");

    String status;

}

