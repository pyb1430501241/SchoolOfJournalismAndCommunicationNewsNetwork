package com.pdsu.sojacnn.factory;

import com.pdsu.sojacnn.bean.NewsContype;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author:wl
 * @Date:2021/5/11 21:39
 * @projectName:sojacnn
 * @description:
 */
@Component
public class NewsContypeFactory extends AbstractFactory<NewsContype>{

    public NewsContypeFactory() {
        super(NewsContype.class);
    }

}
