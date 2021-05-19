package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;

/**
 * @author 半梦
 * @create 2021-05-19 18:21
 */
public interface AbstractController {

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
