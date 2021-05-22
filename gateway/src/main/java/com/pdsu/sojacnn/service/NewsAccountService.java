package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsAccount;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
public interface NewsAccountService extends IService<NewsAccount> {

    String ID = "id";

    String ACCOUNT = "account";

    String PASSWORD = "password";

    String USERNAME = "user_name";

    String CREATE_TIME = "create_time";

}
