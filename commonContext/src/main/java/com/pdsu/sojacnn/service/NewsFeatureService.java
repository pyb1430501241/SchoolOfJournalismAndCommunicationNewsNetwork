package com.pdsu.sojacnn.service;

import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.service.impl.NewsFeatureFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
    public Result insertNewsTheme(@RequestParam("newsTheme") String newsTheme
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @GetMapping("/newsTheme/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id);

    @PostMapping("/newsTheme/updateNewsThemeById")
    public Result updateNewsThemeById(@RequestParam("newsTheme") String newsTheme
           , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/newsTheme/deleteNewsThemeById")
    public Result deleteNewsThemeById(@RequestParam("id") Long id
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/contype/insertContypeById")
    public Result insertContype(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/contype/deleteContypeById")
    public Result deleteContypeById(@RequestParam("id") Integer id
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/contype/updateContypeById")
    public Result updateContypeById(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @GetMapping("/category/findCategoryById")
    public Result findCategoryById(@RequestParam("id") Integer id);

    @PostMapping("/category/insertNewsCategory")
    public Result insertNewsCategory(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/category/deleteCategoryById")
    public Result deleteNewsCategoryById(@RequestParam("id") Integer id
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @PostMapping("/category/updateCategoryById")
    public Result updateNewsCategory(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole);

    @GetMapping("/newsTheme/findByTypeIdAndCategoryId")
    public Result findNewsThemesByTypeIdAndCategoryId(@RequestParam("contypeId") Integer typeId
            , @RequestParam("categoryId") Integer categoryId, @RequestParam("p") Integer p);

}
