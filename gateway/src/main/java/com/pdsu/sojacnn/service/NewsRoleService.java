package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
public interface NewsRoleService extends IService<NewsRole> {

    String ID = "id";

    String ROLE_NAME = "role_name";

}
