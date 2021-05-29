package com.pdsu.sojacnn.utils;

import java.util.function.Function;

/**
 * @author 半梦
 * @create 2021-05-29 16:19
 */
public abstract class FunctionUtils {

    /**
     * 对数据进行修改
     * @param function 接口
     * @param t 传入参
     * @param <T> 传入类型
     * @param <R> 返回类型
     */
    public static <T, R> R function(Function<T, R> function, T t) {
        return function.apply(t);
    }

}
