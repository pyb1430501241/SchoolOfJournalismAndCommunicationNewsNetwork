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

    String ID = "id";

    String TITLE = "title";

    String DATA = "data";

    String COVER_PATH = "cover_path";

    String CREATE_TIME = "create_time";

    String UPDATE_TIME = "update_time";

    String TYPE_ID = "contype_id";

    String CATEGORY_ID = "category_id";

    public void findNewsThemesByTypeIdAndCategoryId(Page<NewsTheme> page, Integer typeId, Integer categoryId);

    public boolean isOnlyOneNewsTheme(Integer typeId, Integer categoryId);

    public NewsTheme findNewsThemeByTypeIdAndCategoryId(Integer typeId, Integer categoryId);

}
