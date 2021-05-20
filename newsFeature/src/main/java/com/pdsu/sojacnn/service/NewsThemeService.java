package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsTheme;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
public interface NewsThemeService extends IService<NewsTheme> {

    public void findNewsThemesByTypeIdAndCategoryId(Page<NewsTheme> page, Integer typeId, Integer categoryId);

}
