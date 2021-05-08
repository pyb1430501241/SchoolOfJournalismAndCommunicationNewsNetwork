package com.pdsu.sojacnn.service.impl;

import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.mapper.NewsContypeMapper;
import com.pdsu.sojacnn.service.NewsContypeService;
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
@Service("newsContypeService")
public class NewsContypeServiceImpl extends ServiceImpl<NewsContypeMapper, NewsContype> implements NewsContypeService {

}
