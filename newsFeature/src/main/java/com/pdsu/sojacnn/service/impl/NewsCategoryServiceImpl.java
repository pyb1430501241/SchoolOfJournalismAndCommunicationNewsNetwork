package com.pdsu.sojacnn.service.impl;

import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.mapper.NewsCategoryMapper;
import com.pdsu.sojacnn.service.NewsCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
@Service("newsCategoryService")
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements NewsCategoryService {

}
