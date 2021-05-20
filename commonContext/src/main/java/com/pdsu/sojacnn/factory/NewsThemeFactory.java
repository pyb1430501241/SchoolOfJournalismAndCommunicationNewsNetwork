package com.pdsu.sojacnn.factory;

import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.utils.DateUtils;
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
 * @create 2021-05-10 17:54
 * @see AbstractFactory
 */
@Component
@SuppressWarnings("all")
public class NewsThemeFactory extends AbstractFactory<NewsTheme> {

    private static final String DEFAULT_COVER_PATH = "1.png";

    private static final String FIELD_NAME_ID = "id";
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

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data,
                            @NonNull Integer contypeId, @Nullable Integer categoryId) throws NoSuchMethodException {
        return create(title, data, contypeId, categoryId, DEFAULT_COVER_PATH);
    }

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data,
                            @NonNull Integer contypeId, @Nullable Integer categoryId, @NonNull String coverPath) throws NoSuchMethodException {
        return create(title, data, contypeId, categoryId, coverPath,false, false);
    }

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, @NonNull String coverPath,boolean createTime, boolean updateTime) throws NoSuchMethodException {
        return create(null, title, data, contypeId, categoryId, DEFAULT_COVER_PATH, createTime, updateTime);
    }

    /**
     * 返回文章
     * @param id 可以为 null, 采用雪花算法
     * @param title 新闻名
     * @param data 新闻主体
     * @param contypeId 新闻类型
     * @param categoryId 新闻类别
     * @param coverPath 图像地址, 默认 01.png
     * @param createTime true 为当前时间, 反之 null
     * @param updateTime true 为当前时间, 反之 null
     * @return 一个 NewsTheme 对象
     * @see NewsTheme
     * @throws NoSuchMethodException
     *  无 get set 方法
     */
    @NonNull
    public NewsTheme create(@Nullable Long id, @NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, @NonNull String coverPath,boolean createTime, boolean updateTime) throws NoSuchMethodException {
        Map<String, Object> args = new LinkedHashMap<>();
        args.put(FIELD_NAME_ID, id);
        args.put(FIELD_NAME_TITLE, title);
        args.put(FIELD_NAME_DATA, data.getBytes());
        args.put(FIELD_NAME_CONTYPE_ID, contypeId);
        args.put(FIELD_NAME_CATEGORY_ID, categoryId);
        args.put(FIELD_NAME_COVER_PATH, coverPath);
        if(createTime) {
            args.put(FIELD_NAME_CREATE_TIME, DateUtils.nowDate());
        }
        if(updateTime) {
            args.put(FIELD_NAME_UPDATE_TIME, DateUtils.nowDate());
        }
        return create(args);
    }

    @NonNull
    public NewsTheme create(@Nullable Long id, @NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, boolean createTime, boolean updateTime) throws NoSuchMethodException {
        return create(id, title, data, contypeId, categoryId, DEFAULT_COVER_PATH, createTime, updateTime);
    }

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, boolean createTime, boolean updateTime) throws NoSuchMethodException {
        return create(title, data, contypeId, categoryId, DEFAULT_COVER_PATH, createTime, updateTime);
    }

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, boolean updateTime) throws NoSuchMethodException {
        return create(title, data, contypeId, categoryId, DEFAULT_COVER_PATH, updateTime);
    }

    @NonNull
    public NewsTheme create(@NonNull String title, @NonNull String data, @NonNull Integer contypeId
            , @Nullable Integer categoryId, @NonNull String coverPath, boolean updateTime) throws NoSuchMethodException {
        return create(title, data, contypeId, categoryId, coverPath,false, updateTime);
    }

}
