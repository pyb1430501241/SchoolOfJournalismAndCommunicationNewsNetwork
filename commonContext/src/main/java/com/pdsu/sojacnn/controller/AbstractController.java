package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.exception.NewsException;
import com.pdsu.sojacnn.utils.JsonUtils;
import org.jetbrains.annotations.Contract;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/**
 * Controller 均要实现此接口, 为其提供便捷的 JSON 化, 以及默认的一些关键字
 * @author 半梦
 * @create 2021-05-19 18:21
 */
public interface AbstractController {

    /**
     * 默认的类别ID，即其他
     */
     Integer DEFAULT_CATEGORY_ID = 1;

    /**
     * 一般用于未被定义的错误
     */
    String DEFAULT_ERROR_PROMPT = "未定义类型错误";

    String NOT_LOGIN = "未登录";

    /**
     * 用户认证信息
     */
    String ACCOUNT_SESSION_FLAG = "authorization";

    /**
     * 默认一页多少条数据
     */
    Integer DEFAULT_PAGE_SIZE = 8;

    /**
     * 用在返回单个对象
     */
    String DEFAULT_MESSAGE_NAME = "item";

    /**
     * 用在返回集合
     */
    String DEFAULT_MESSAGES_NAME = "items";

    /**
     * 有没有下一页
     */
    String HAS_NEXT_PAGE = "isLastPage";

    /**
     * 有没有上一页
     */
    String HAS_PREVIOUS_PAGE = "hasPreviousPage";

    /**
     * 第几页
     */
    String PAGE_NUMBER = "rows";

    /**
     * 总共多少页
     */
    String PAGE_TOTAL = "totals";

    /**
     * 响应提示信息
     */
    String MESSAGE = "message";

    /**
     * 默认编码
     */
    String DEFAULT_CODING = "UTF-8";

    /**
     * 对象转 json
     */
    @NonNull
    default String parseJson(@Nullable Object obj) throws NewsException{
        String s = JsonUtils.valueOfString(obj);
        if(!StringUtils.hasText(s)) {
            throw new NewsException("JSON 格式化异常");
        }
        return s;
    }

    /**
     * json 转对象
     */
    @NonNull
    @Contract("null,_ -> fail")
    default <T> T parseObject(@NonNull String json, @NonNull Class<T> clazz) throws NewsException {
        T t = JsonUtils.ObjectOfString(json, clazz);
        if(t == null) {
            throw new NewsException("JSON 转对象异常");
        }
        return t;
    }

}
