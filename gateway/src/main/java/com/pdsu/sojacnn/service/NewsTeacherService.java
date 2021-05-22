package com.pdsu.sojacnn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pdsu.sojacnn.bean.NewsTeacher;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
public interface NewsTeacherService extends IService<NewsTeacher> {

    String ID = "id";

    String NAME = "name";

    String INTRODUCTION = "introduction";

    String PHONE_NUMBER = "phone_number";

}
