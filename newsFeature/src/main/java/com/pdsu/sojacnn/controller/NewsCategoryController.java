package com.pdsu.sojacnn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsCategoryService;
import com.pdsu.sojacnn.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/category")
public class NewsCategoryController extends AuthenticationController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @GetMapping("/findCategoryById")
    public Result findCategoryById(@RequestParam("id") Integer id) throws Exception {
        NewsCategory newsCategory = newsCategoryService.getById(id);
        return newsCategory == null ? Result.notFound() :Result.ok().data(DEFAULT_MESSAGE_NAME, newsCategory);
    }

    @PostMapping("/deleteCategoryById")
    public Result deleteCategoryById(@RequestParam("id") Integer id
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        boolean b = newsCategoryService.removeById(id);
        if(b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/insertNewsCategory")
    public Result insertCategory(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsCategoryService.save(parseObject(newsCategory, NewsCategory.class));
        return Result.ok();
    }

    @PostMapping("/updateCategoryById")
    public Result updateCategoryById(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsCategoryService.updateById(parseObject(newsCategory,NewsCategory.class));
        return Result.ok();
    }

    @GetMapping("/findCategoryIdByContypeId")
    public Result findCategoryIdByContypeId(@RequestParam("contypeId") Integer contypeId
            , @RequestParam("p") Integer p) throws Exception {
        Page<NewsCategory> page = new Page<>(p, DEFAULT_PAGE_SIZE);
        newsCategoryService.findCategoryIdByContypeId(page, contypeId);

        List<NewsCategory> newsCategories = page.getRecords().stream().peek(e -> {

        }).collect(Collectors.toList());
        page.setRecords(newsCategories);
        return PageUtils.defaultPage(Result.ok(), page);
    }
}

