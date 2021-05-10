package com.pdsu.sojacnn.utils;

import org.springframework.lang.NonNull;

/**
 * @author 半梦
 * @create 2021-05-10 20:56
 */
public abstract class HashUtils {

    @NonNull
    public String encrypt(@NonNull String salt, @NonNull String value) {
        return salt + value;
    }

}
