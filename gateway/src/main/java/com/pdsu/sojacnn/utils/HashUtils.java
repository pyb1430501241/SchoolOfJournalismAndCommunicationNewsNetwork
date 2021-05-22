package com.pdsu.sojacnn.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author 半梦
 * @create 2021-05-21 22:13
 */
public abstract class HashUtils {

    /**
     * MD5 加密
     * @param account 盐
     * @param password 加密对象
     */
    public static String md5(String account, String password) {
        SimpleHash hash = new SimpleHash("MD5", password, account, 2);
        return hash.toString();
    }

}
