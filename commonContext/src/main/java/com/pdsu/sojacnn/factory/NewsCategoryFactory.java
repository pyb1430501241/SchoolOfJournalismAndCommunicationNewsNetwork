package com.pdsu.sojacnn.factory;

import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsContype;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 半梦
 * @author wl
 * @Date:2021/5/12 21:22
 * @projectName:sojacnn
 */
@Component
public class NewsCategoryFactory extends AbstractFactory<NewsCategory>{

    private static final String FIELD_NAME_ID = "id";
    private static final String FIELD_NAME_CATEGORY_NAME = "categoryName";
    private static final String FIELD_NAME_CONTYPE_ID = "contypeId";

    private static final Logger log = LoggerFactory.getLogger(NewsThemeFactory.class);

    public NewsCategoryFactory() {
        super(NewsCategory.class);
    }

    @NonNull
    public NewsCategory create(@Nullable Integer id, @NonNull String categoryName, @NonNull Integer contypeId) throws NoSuchMethodException {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put(FIELD_NAME_ID, id);
        map.put(FIELD_NAME_CATEGORY_NAME, categoryName);
        map.put(FIELD_NAME_CONTYPE_ID, contypeId);
        return create(map);
    }

    @NonNull
    public NewsCategory create(@NonNull String categoryName, @NonNull Integer categoryId) throws NoSuchMethodException {
        return create(null, categoryName, categoryId);
    }
}
