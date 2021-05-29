package com.pdsu.sojacnn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsTheme;
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
 * @author wl
 * @since 2021-05-07
 */
@Service("newsCategoryService")
public class NewsCategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements NewsCategoryService {

    @Override
    public void findCategoryIdByContypeId(Page<NewsCategory> page, Integer contypeId) {
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contype_id", contypeId);
        baseMapper.selectPage(page, queryWrapper);
    }
}
