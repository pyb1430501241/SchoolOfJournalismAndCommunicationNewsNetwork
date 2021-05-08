package com.pdsu.sojacnn.service;

import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.impl.NewsFeatureFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 半梦
 * @create 2021-05-08 15:32
 */
@SuppressWarnings("all")
@Service
@FeignClient(value = NewsFeatureService.PROVIDER_NAME, fallbackFactory = NewsFeatureFallbackFactory.class)
public interface NewsFeatureService {

    String PROVIDER_NAME = "NEWSFEATURE";

    @GetMapping("/contype/findContypeById")
    public Result findContypeById(@RequestParam("id") Integer id);

    @PostMapping("/newsTheme/insert")
    public Result insertNewsTheme(@RequestBody NewsTheme newsTheme);

}
