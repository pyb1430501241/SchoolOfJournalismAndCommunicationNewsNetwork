package com.pdsu.sojacnn.factory;

import com.pdsu.sojacnn.bean.NewsTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 半梦
 * @create 2021-05-10 17:54
 */
@Component
@SuppressWarnings("all")
public class NewsThemeFactory extends AbstractFactory<NewsTheme> {

    private static final String FIELD_NAME_TITLE = "title";
    private static final String FIELD_NAME_DATA = "data";
    private static final String FIELD_NAME_COVER_PATH = "coverPath";
    private static final String FIELD_NAME_CREATE_TIME = "createTime";
    private static final String FIELD_NAME_UPDATE_TIME = "updateTime";
    private static final String FIELD_NAME_CONTYPE_ID = "contypeId";
    private static final String FIELD_NAME_CATEGORY_ID = "categoryId";

    private static final Logger log = LoggerFactory.getLogger(NewsThemeFactory.class);

    public NewsThemeFactory() {
        super(NewsTheme.class);
    }

    /**
     * 返回文章
     * @param title 新闻名
     * @param data 新闻主体
     * @param contypeId 新闻类型
     * @param categoryId 新闻类别
     * @return
     * @throws NoSuchMethodException
     */
    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data,
                            @NonNull Integer contypeId, @Nullable Integer categoryId) throws NoSuchMethodException {
        Map<String, Object> args = new HashMap<>();
        args.put(FIELD_NAME_TITLE, title);
        args.put(FIELD_NAME_DATA, data.getBytes());
        args.put(FIELD_NAME_CONTYPE_ID, contypeId);
        args.put(FIELD_NAME_CATEGORY_ID, categoryId);
        return create(args);
    }

}
