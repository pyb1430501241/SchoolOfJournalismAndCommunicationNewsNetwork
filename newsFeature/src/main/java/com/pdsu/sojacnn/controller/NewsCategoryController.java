package com.pdsu.sojacnn.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsCategoryService;
import com.pdsu.sojacnn.utils.PageUtils;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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

        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        log.info("用户{}已通过权限校验, 可以更改类别, 其权限至少为: {}", newsAccountRole, SUPER_ADMIN);

        boolean b = newsCategoryService.removeById(id);
        if(b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/insertNewsCategory")
    public Result insertCategory(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        log.info("用户{}已通过权限校验, 可以更改类型, 其权限至少为: {}", newsAccountRole, SUPER_ADMIN);

        newsCategoryService.save(parseObject(newsCategory, NewsCategory.class));
        return Result.ok();
    }

    @PostMapping("/updateCategoryById")
    public Result updateCategoryById(@RequestParam("newsCategory") String newsCategory
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        log.info("用户{}已通过权限校验, 可以更改类型, 其权限至少为: {}", newsAccountRole, SUPER_ADMIN);

        newsCategoryService.updateById(parseObject(newsCategory,NewsCategory.class));
        return Result.ok();
    }

    @GetMapping("/findCategoryIdByContypeId")
    public Result findCategoryIdByContypeId(@RequestParam("contypeId") Integer contypeId
            , @RequestParam("p") Integer p) throws Exception {
        Page<NewsCategory> page = new Page<>(p, DEFAULT_PAGE_SIZE);

        newsCategoryService.findCategoryIdByContypeId(page, contypeId);

        return PageUtils.defaultPage(Result.ok(), page);
    }
}

