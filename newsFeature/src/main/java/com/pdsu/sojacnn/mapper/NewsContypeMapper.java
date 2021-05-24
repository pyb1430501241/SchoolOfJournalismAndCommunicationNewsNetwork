package com.pdsu.sojacnn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pdsu.sojacnn.bean.NewsContype;
import org.apache.ibatis.annotations.CacheNamespace;
import org.mybatis.caches.ehcache.EhcacheCache;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
@Repository
@CacheNamespace(implementation = EhcacheCache.class, eviction = EhcacheCache.class)
public interface NewsContypeMapper extends BaseMapper<NewsContype> {

}
