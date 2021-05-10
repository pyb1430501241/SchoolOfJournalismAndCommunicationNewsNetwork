package com.pdsu.sojacnn.service.impl;

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

}