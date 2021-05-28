package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsThemeService;
import com.pdsu.sojacnn.utils.JsonUtils;
import com.pdsu.sojacnn.utils.PageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
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
    public Result insertNewsTheme(@RequestParam("newsTheme") String newsTheme
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsThemeService.save(parseObject(newsTheme, NewsTheme.class));

        return Result.ok();
    }

    @GetMapping("/findNewsThemeById")
    public Result findNewsThemeById(@RequestParam("id") Long id) throws Exception {
        NewsTheme newsTheme = newsThemeService.getById(id);
        return newsTheme == null ? Result.notFound() :Result.ok().data(DEFAULT_MESSAGE_NAME, newsTheme);
    }

    @PostMapping("/updateNewsThemeById")
    public Result updateNewsThemeById(@RequestParam("newsTheme") String newsTheme
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        // debug info warn error
        // debug 开发时需要定位代码执行位置使用（信息没用，只是为了调试方便）
        // info 系统信息, 即保留 debug 功能的同时, 进行日志的记录（信息是有用的）
        // warn 系统有不致命异常（可能发生一些不影响运行的异常）
        // error 系统已发生异常, 记录异常日志

        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        log.info("用户已通过权限校验, 可以更改新闻, 其权限至少为: " + BASIC_PERSONNEL);

        // before after afterException

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
        Page<NewsTheme> page = new Page<>(p, DEFAULT_PAGE_SIZE);
        newsThemeService.findNewsThemesByTypeIdAndCategoryId(page, typeId, categoryId);
        List<NewsTheme> news = page.getRecords().stream().peek(e -> {
            if (e.getData() != null) {
                //try {
                //    e.setDataString(new String(e.getData(), DEFAULT_CODING));
                e.setData(null);
                //} catch (UnsupportedEncodingException ex) {
                //    if(log.isDebugEnabled()) {
                //        log.debug(ex.getMessage());
                //    }
                //}
            }
        }).collect(Collectors.toList());
        page.setRecords(news);
        return PageUtils.defaultPage(Result.ok(), page);
    }

}

