package com.pdsu.sojacnn.exception.account;

import org.apache.shiro.authc.CredentialsException;

/**
 * @author 半梦
 * @create 2021-05-20 18:27
 */
public class AccountAbnormalException extends CredentialsException {

    public AccountAbnormalException() {
    }

    public AccountAbnormalException(String message) {
        super(message);
    }
}
