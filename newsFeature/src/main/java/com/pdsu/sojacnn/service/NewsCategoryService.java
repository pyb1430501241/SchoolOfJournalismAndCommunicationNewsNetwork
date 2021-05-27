package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsCategory;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @author wl
 * @since 2021-05-07
 */
public interface NewsCategoryService extends IService<NewsCategory> {

    String ID = "id";

    String CATEGORY_NAME = "category_name";

    String TYPE_ID = "contype_id";


    void findCategoryIdByContypeId(Page<NewsCategory> page, Integer contypeId);
}
