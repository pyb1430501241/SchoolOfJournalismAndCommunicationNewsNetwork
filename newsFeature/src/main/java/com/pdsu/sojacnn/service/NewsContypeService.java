package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsContype;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
public interface NewsContypeService extends IService<NewsContype> {

    String ID = "id";

    String TYPE_NAME = "contype_name";

}
