package com.pdsu.sojacnn.service.impl;

import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.service.NewsFeatureService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
        public Result insertNewsTheme(String newsTheme, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result findNewsThemeById(Long id) {
            return RESULT;
        }

        @Override
        public Result updateNewsThemeById(String newsTheme, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result deleteNewsThemeById(Long id, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result insertContype(String newsContype, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result deleteContypeById(Integer id, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result updateContypeById(String newsContype, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result findCategoryById(Integer id) {
            return RESULT;
        }

        @Override
        public Result insertNewsCategory(String newsCategory, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result deleteNewsCategoryById(Integer id, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result updateNewsCategory(String newsCategory, String newsAccountRole) {
            return RESULT;
        }

        @Override
        public Result findNewsThemesByTypeIdAndCategoryId(Integer typeId, Integer categoryId, Integer p) {
            return RESULT;
        }
    }

}
