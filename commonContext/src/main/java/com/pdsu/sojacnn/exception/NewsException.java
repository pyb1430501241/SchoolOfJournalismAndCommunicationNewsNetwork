package com.pdsu.sojacnn.exception;

import lombok.NoArgsConstructor;

/**
 * @author 半梦
 * @create 2021-05-20 18:24
 */
@NoArgsConstructor
public class NewsException extends Exception {

    public NewsException(String message) {
        super(message);
    }

}
