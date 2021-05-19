package com.pdsu.sojacnn.service.impl;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.BackgroundService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Repository;

/**
 * @author 半梦
 * @create 2021-05-11 20:46
 */
@Repository
public class BackgroundServiceFallbackFactory implements FallbackFactory<BackgroundService> {
    @Override
    public BackgroundService create(Throwable cause) {
        return new BackgroundServiceFallBack();
    }

    @SuppressWarnings("all")
    public static class BackgroundServiceFallBack implements BackgroundService {

        private static final Result RESULT = Result.fail();

        @Override
        public Result findNewsRoles(Integer p) {
            return RESULT;
        }
    }

}
