package com.pdsu.sojacnn.service;

import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
public interface NewsAccountRoleService extends IService<NewsAccountRole> {

    String ID = "id";

    String ACCOUNT_ID = "account_id";

    String ROLE_ID = "role_id";

}
