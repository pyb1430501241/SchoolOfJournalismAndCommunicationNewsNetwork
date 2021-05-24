package com.pdsu.sojacnn.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * json 格式化工具
 * @author 半梦
 * @create 2021-05-22 18:22
 */
@Log4j2
public abstract class JsonUtils {

    private static final ObjectMapper JSON_PARSE = new ObjectMapper();

    static {
        JSON_PARSE.setDateFormat(DateUtils.DATE_FORMAT);
    }

    /**
     * 将对象转为 json
     */
    @Nullable
    public static String valueOfString(@NonNull Object obj) {
        try {
            return JSON_PARSE.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.debug("json 转换异常", e);
        }
        return null;
    }

    /**
     * 将 json 转为对象
     */
    @Nullable
    @Contract("null, _ -> null")
    public static <T> T ObjectOfString(@Nullable String json, Class<T> clazz) {
        if(Objects.isNull(json)) {
            return null;
        }
        try {
            return JSON_PARSE.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.debug("json 转换异常", e);
            return null;
        }
    }

}
