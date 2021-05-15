package com.pdsu.sojacnn.controller;


import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 半梦
 * @author wl
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/newsTheme")
public class NewsThemeController {

    @Autowired
    private NewsThemeService newsThemeService;

    @PostMapping("/insert")
    public Result insertNewsTheme(@RequestBody NewsTheme newsTheme) {
        newsThemeService.save(newsTheme);
        return Result.ok();
    }

    @GetMapping("/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id) {
        NewsTheme newsTheme = newsThemeService.getById(id);
        return newsTheme == null ? Result.notFound() :Result.ok().data("item", newsTheme);
    }

    @PostMapping("/updateNewsThemeById")
    public Result updateNewsThemeById(@RequestBody NewsTheme newsTheme) {
        newsThemeService.updateById(newsTheme);
        return Result.ok();
    }

    @PostMapping("/deleteNewsThemeById")
    public Result deleteNewsThemeById(@RequestParam("id") Long id) {
        newsThemeService.removeById(id);
        return Result.ok();
    }

}

