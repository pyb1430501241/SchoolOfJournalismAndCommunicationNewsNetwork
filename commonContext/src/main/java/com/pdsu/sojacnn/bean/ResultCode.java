package com.pdsu.sojacnn.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @create 2021-05-07 19:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ResultCode {

    OK(200, true, "ok"),
    FAIL(500, false, "fail"),
    NOTFOUND(404, false, "not found"),
    PERMISSION(401, false, "insufficient permissions");

    private Integer code;

    private Boolean success;

    private String msg;

}
