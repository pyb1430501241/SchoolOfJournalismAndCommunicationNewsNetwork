package com.pdsu.sojacnn.controller;

/**
 * @author 半梦
 * @create 2021-05-19 18:21
 */
public interface AbstractController {

    String DEFAULT_ERROR_PROMPT = "未定义类型错误";

    String NOT_LOGIN = "未登录";

    String ACCOUNT_SESSION_FLAG = "account";

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

}