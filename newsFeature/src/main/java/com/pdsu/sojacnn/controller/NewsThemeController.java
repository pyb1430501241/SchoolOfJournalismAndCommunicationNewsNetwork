package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsThemeService;
import com.pdsu.sojacnn.utils.PageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

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
@Log4j2
public class NewsThemeController extends AuthenticationController {

    @Autowired
    private NewsThemeService newsThemeService;

    @PostMapping("/insert")
    public Result insertNewsTheme(@RequestBody NewsTheme newsTheme) throws Exception {
        newsThemeService.save(newsTheme);
        return Result.ok();
    }

    @GetMapping("/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id) throws Exception {
        NewsTheme newsTheme = newsThemeService.getById(id);
        return newsTheme == null ? Result.notFound() :Result.ok().data(DEFAULT_MESSAGE_NAME, newsTheme);
    }

    @PostMapping("/updateNewsThemeById")
    public Result updateNewsThemeById(@RequestBody NewsTheme newsTheme) throws Exception {
        newsThemeService.updateById(newsTheme);
        return Result.ok();
    }

    @PostMapping("/deleteNewsThemeById")
    public Result deleteNewsThemeById(@RequestParam("id") Long id) throws Exception {
        newsThemeService.removeById(id);
        return Result.ok();
    }

    @GetMapping("/findByTypeIdAndCategoryId")
    public Result findNewsThemesByTypeIdAndCategoryId(@RequestParam("contypeId") Integer typeId
            , @RequestParam Integer categoryId, @RequestParam Integer p) throws Exception {
        Page<NewsTheme> page = new Page<>(p, 5);
        newsThemeService.findNewsThemesByTypeIdAndCategoryId(page, typeId, categoryId);
        List<NewsTheme> news = page.getRecords().stream().peek(e -> {
            if (e.getData() != null) {
                try {
                    e.setDataString(new String(e.getData(), DEFAULT_CODING));
                    e.setData(null);
                } catch (UnsupportedEncodingException ex) {
                    if(log.isDebugEnabled()) {
                        log.debug(ex.getMessage());
                    }
                }
            }
        }).collect(Collectors.toList());
        page.setRecords(news);
        return PageUtils.defaultPage(Result.ok(), page);
    }

}

