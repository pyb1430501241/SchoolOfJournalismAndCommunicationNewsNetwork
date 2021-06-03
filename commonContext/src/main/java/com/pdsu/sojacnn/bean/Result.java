package com.pdsu.sojacnn.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 半梦
 * @create 2021-05-07 19:11
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result implements Serializable {

    private Integer code;

    private Boolean success;

    private String msg;

    private Map<String, Object> data = new HashMap<>();

    private Result() {
    }

    public static Result ok() {
        return field(createResult(), ResultCode.OK);
    }

    public static Result fail() {
        return field(createResult(), ResultCode.FAIL);
    }

    public static Result insufficientPermissions() {
        return field(createResult(), ResultCode.PERMISSION);
    }

    public static Result notFound() {
        return field(createResult(), ResultCode.NOTFOUND);
    }

    // 链式编程
    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    private static Result createResult() {
        return new Result();
    }

    private static Result field(Result result, ResultCode code) {
        result.setCode(code.getCode());
        result.setSuccess(code.getSuccess());
        result.setMsg(code.getMsg());
        return result;
    }
}
