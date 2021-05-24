package com.pdsu.sojacnn.service;

import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.impl.BackgroundServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 半梦
 * @create 2021-05-11 20:46
 */
@Service
@FeignClient(name = BackgroundService.PROVIDER_NAME, fallbackFactory = BackgroundServiceFallbackFactory.class)
@SuppressWarnings("all")
public interface BackgroundService {

    String PROVIDER_NAME = "BACKGROUNDSYSTEM";

    @GetMapping("/newsRole/findAll")
    public Result findNewsRoles(@RequestParam(value = "p") Integer p, @SpringQueryMap NewsAccountRole newsAccountRole);

}
