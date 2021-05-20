package com.pdsu.sojacnn.factory;

import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.utils.DateUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wl
 * @author 半梦
 * @Date 2021/5/11 21:39
 * @projectName sojacnn
 * @description
 */
@Component
public class NewsContypeFactory extends AbstractFactory<NewsContype>{

    public NewsContypeFactory() {
        super(NewsContype.class);
    }

    @NonNull
    public NewsContype create(@NonNull String contypeName) throws NoSuchMethodException{
        return create(null, contypeName);
    }

    @NonNull
    public NewsContype create(@Nullable Integer id, @NonNull String contypeName) throws NoSuchMethodException {
        NewsContype contype = create();
        contype.setContypeName(contypeName);
        contype.setId(id);
        return contype;
    }

}
