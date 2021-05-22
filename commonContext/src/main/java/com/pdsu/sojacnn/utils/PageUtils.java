package com.pdsu.sojacnn.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.controller.AbstractController;
import org.springframework.lang.NonNull;

/**
 * 分页工具
 * @author 半梦
 * @create 2021-05-19 20:09
 */
public abstract class PageUtils {

    private static final String DEFAULT_MESSAGES_NAME = AbstractController.DEFAULT_MESSAGES_NAME;
    private static final String HAS_NEXT_PAGE = AbstractController.HAS_NEXT_PAGE;
    private static final String HAS_PREVIOUS_PAGE = AbstractController.HAS_PREVIOUS_PAGE;
    private static final String PAGE_NUMBER = AbstractController.PAGE_NUMBER;
    private static final String PAGE_TOTAL = AbstractController.PAGE_TOTAL;

    @NonNull
    public static Result defaultPage(@NonNull Result result, @NonNull Page<?> page) {
        return result.data(DEFAULT_MESSAGES_NAME, page.getRecords())
                .data(HAS_NEXT_PAGE, page.hasNext())
                .data(HAS_PREVIOUS_PAGE, page.hasPrevious())
                .data(PAGE_NUMBER, page.getCurrent())
                .data(PAGE_TOTAL, page.getPages());
    }

}
