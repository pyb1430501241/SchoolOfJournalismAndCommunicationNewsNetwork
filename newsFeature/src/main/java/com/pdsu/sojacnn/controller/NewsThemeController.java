package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsThemeService;
import com.pdsu.sojacnn.utils.FunctionUtils;
import com.pdsu.sojacnn.utils.PageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

    private final NewsThemeService newsThemeService;

    @Autowired
    public NewsThemeController(NewsThemeService newsThemeService) {
        this.newsThemeService = newsThemeService;
    }

    @PostMapping("/insert")
    public Result insertNewsTheme(@RequestParam("newsTheme") String newsTheme
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsThemeService.save(parseObject(newsTheme, NewsTheme.class));

        return Result.ok();
    }

    @GetMapping("/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id) throws Exception {
        NewsTheme newsTheme = newsThemeService.getById(id);
        return newsTheme == null ? Result.notFound() : Result.ok().data(DEFAULT_MESSAGE_NAME, updateNewsTheme(newsTheme));
    }

    @PostMapping("/updateNewsThemeById")
    public Result updateNewsThemeById(@RequestParam("newsTheme") String newsTheme
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {

        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsThemeService.updateById(parseObject(newsTheme, NewsTheme.class));

        return Result.ok();
    }

    @PostMapping("/deleteNewsThemeById")
    public Result deleteNewsThemeById(@RequestParam("id") Long id
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsThemeService.removeById(id);

        return Result.ok();
    }

    @GetMapping("/findByTypeIdAndCategoryId")
    public Result findNewsThemesByTypeIdAndCategoryId(@RequestParam("contypeId") Integer typeId
            , @RequestParam Integer categoryId, @RequestParam Integer p) throws Exception {
        log.info("获取相应的文章, 类型: {}, 类别: {}", typeId, categoryId);

        if(newsThemeService.isOnlyOneNewsTheme(typeId, categoryId)) {
            log.info("相应的文章只有一篇, 故返回一篇文章");
            return Result.ok().data(DEFAULT_MESSAGE_NAME,
                    updateNewsTheme(newsThemeService.findNewsThemeByTypeIdAndCategoryId(typeId, categoryId)))
                    .data("isOnly", true);
        }

        Page<NewsTheme> page = new Page<>(p, DEFAULT_PAGE_SIZE);
        newsThemeService.findNewsThemesByTypeIdAndCategoryId(page, typeId, categoryId);
        List<NewsTheme> news = page.getRecords().stream().peek(e -> e.setData(null)).collect(Collectors.toList());
        page.setRecords(news);
        log.info("获取相应文章成功");
        return PageUtils.defaultPage(Result.ok().data("isOnly", false), page);
    }

    /**
     * 把 byte 转为 String
     */
    private NewsTheme updateNewsTheme(NewsTheme newsTheme) {
        return FunctionUtils.function(e -> {
            try {
                e.setDataString(new String(e.getData(), DEFAULT_CODING));
            } catch (UnsupportedEncodingException ex) {
                log.warn("类型转换异常, 异常信息", ex);
            }
            e.setData(null);
            if (e.getCategoryId() == null) {
                e.setCategoryId(DEFAULT_CATEGORY_ID);
            }
            return e;
        }, newsTheme);
    }

}

