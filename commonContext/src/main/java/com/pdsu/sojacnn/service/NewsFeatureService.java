package com.pdsu.sojacnn.service;

import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsContype;
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

    @GetMapping("/newsTheme/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id);

    @PostMapping("/newsTheme/updateNewsThemeById")
    Result updateNewsThemeById(@RequestBody NewsTheme newsTheme);

    @PostMapping("/newsTheme/deleteNewsThemeById")
    Result deleteNewsThemeById(@RequestParam("id") Long id);

    @PostMapping("/contype/insertContypeById")
    Result insertContype(@RequestBody NewsContype newsContype);

    @PostMapping("/contype/deleteContypeById")
    Result deleteContypeById(@RequestParam("id") Integer id);

    @PostMapping("/contype/updateContypeById")
    Result updateContypeById(@RequestBody NewsContype newsContype);

    @GetMapping("/category/findCategoryById")
    Result findCategoryById(@RequestParam("id") Integer id);

    @PostMapping("/category/insertNewsCategory")
    Result insertNewsCategory(@RequestBody NewsCategory newsCategory);

    @PostMapping("/category/deleteCategoryById")
    Result deleteNewsCategoryById(@RequestParam("id") Integer id);

    @PostMapping("/category/updateCategoryById")
    Result updateNewsCategory(@RequestBody NewsCategory newsCategory);
}
