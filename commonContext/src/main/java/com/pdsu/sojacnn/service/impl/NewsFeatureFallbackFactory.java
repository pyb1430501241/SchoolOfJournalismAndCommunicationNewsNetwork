package com.pdsu.sojacnn.service.impl;

import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsFeatureService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author 半梦
 * @create 2021-05-08 15:34
 * 服务降级
 */
@Repository
public class NewsFeatureFallbackFactory implements FallbackFactory<NewsFeatureService> {

    @Override
    public NewsFeatureService create(Throwable cause) {
        return new NewsFeatureServiceFallBack();
    }

    @SuppressWarnings("all")
    public static class NewsFeatureServiceFallBack implements NewsFeatureService {

        private static final Result RESULT = Result.fail();

        @Override
        public Result findContypeById(Integer id) {
            return RESULT;
        }

        @Override
        public Result insertNewsTheme(NewsTheme newsTheme) {
            return RESULT;
        }

        @Override
        public Result findNewsThemeById(Long id) {
            return RESULT;
        }

        @Override
        public Result updateNewsThemeById(NewsTheme newsTheme) {
            return RESULT;
        }

        @Override
        public Result deleteNewsThemeById(Long id) {
            return RESULT;
        }
    }

}
