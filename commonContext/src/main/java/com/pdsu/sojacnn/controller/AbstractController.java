package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.utils.JsonUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author 半梦
 * @create 2021-05-19 18:21
 */
public interface AbstractController {

    /**
     * 一般用于未被定义的错误
     */
    String DEFAULT_ERROR_PROMPT = "未定义类型错误";

    String NOT_LOGIN = "未登录";

    /**
     * 用户认证信息
     */
    String ACCOUNT_SESSION_FLAG = "Authorization";

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
    String HAS_PREVIOUS_PAGE = "isFirstPage";

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

    @Nullable
    default String parseJson(@Nullable Object obj) {
        return JsonUtils.valueOfString(obj);
    }

    @Nullable
    default <T> T parseObject(@NonNull String json, @NonNull Class<T> clazz) {
        return JsonUtils.ObjectOfString(json, clazz);
    }

}
