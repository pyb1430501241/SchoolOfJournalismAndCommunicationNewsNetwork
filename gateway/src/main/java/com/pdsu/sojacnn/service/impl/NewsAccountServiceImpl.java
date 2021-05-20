package com.pdsu.sojacnn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pdsu.sojacnn.bean.NewsAccount;
import com.pdsu.sojacnn.mapper.NewsAccountMapper;
import com.pdsu.sojacnn.service.NewsAccountService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
@Service
public class NewsAccountServiceImpl extends ServiceImpl<NewsAccountMapper, NewsAccount> implements NewsAccountService {

}
