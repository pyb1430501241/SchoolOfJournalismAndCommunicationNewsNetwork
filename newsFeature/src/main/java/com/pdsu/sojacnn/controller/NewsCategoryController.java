package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsCategoryService;
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
    public Result deleteCategoryById(@RequestParam("id") Integer id) throws Exception {
        boolean b = newsCategoryService.removeById(id);
        if(b) {
            return Result.ok();
        }
        return Result.fail();
    }

    @PostMapping("/insertNewsCategory")
    public Result insertCategory(@RequestBody NewsCategory newsCategory) throws Exception {
        newsCategoryService.save(newsCategory);
        return Result.ok();
    }

    @PostMapping("/updateCategoryById")
    public Result updateCategoryById(@RequestBody NewsCategory newsCategory) throws Exception {
        newsCategoryService.updateById(newsCategory);
        return Result.ok();
    }

}

