package com.pdsu.sojacnn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.mapper.NewsThemeMapper;
import com.pdsu.sojacnn.service.NewsThemeService;
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
@Service("newsThemeService")
public class NewsThemeServiceImpl extends ServiceImpl<NewsThemeMapper, NewsTheme> implements NewsThemeService {

    @Override
    public void findNewsThemesByTypeIdAndCategoryId(Page<NewsTheme> page, Integer typeId, Integer categoryId) {
        QueryWrapper<NewsTheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contype_id", typeId);
        queryWrapper.eq("category_id", categoryId);
        baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public boolean isOnlyOneNewsTheme(Integer typeId, Integer categoryId) {
        QueryWrapper<NewsTheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contype_id", typeId);
        queryWrapper.eq("category_id", categoryId);
        return baseMapper.selectCount(queryWrapper) == 1;
    }

    public NewsTheme findNewsThemeByTypeIdAndCategoryId(Integer typeId, Integer categoryId){
        QueryWrapper<NewsTheme> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("contype_id", typeId);
        queryWrapper.eq("category_id", categoryId);
        return baseMapper.selectOne(queryWrapper);
    }

}
