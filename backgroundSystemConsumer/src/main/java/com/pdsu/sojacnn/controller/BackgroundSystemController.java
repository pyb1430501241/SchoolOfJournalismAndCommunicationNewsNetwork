package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsCategory;
import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.factory.NewsCategoryFactory;
import com.pdsu.sojacnn.factory.NewsContypeFactory;
import com.pdsu.sojacnn.factory.NewsThemeFactory;
import com.pdsu.sojacnn.service.BackgroundService;
import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 半梦
 * @author wl
 * @create 2021-05-11 20:44
 */
@SuppressWarnings("deprecation")
@RestController
@Api(description = "后台管理")
public class BackgroundSystemController {

    @Autowired
    private NewsFeatureService newsFeatureService;

    @Autowired
    private NewsThemeFactory newsThemeFactory;

    @Autowired
    private NewsContypeFactory newsContypeFactory;

    @Autowired
    private NewsCategoryFactory newsCategoryFactory;

    @Autowired
    private BackgroundService backgroundService;

    @GetMapping("/findRoles")
    @ApiOperation(value = "查询所有的角色", response = Result.class)
    public Result findNewsRoles() {
        return backgroundService.findNewsRoles();
    }

    @PostMapping("/insertNewsTheme")
    @ApiOperation(value = "插入新闻主体", response = Result.class)
    public Result insertNewsTheme(String title, String data, Integer contypeId, Integer categoryId) throws Exception {
        NewsTheme newsTheme = newsThemeFactory.create(title, data, contypeId, categoryId);
        newsTheme.setCreateTime(DateUtils.nowDate());
        newsTheme.setUpdateTime(DateUtils.nowDate());
        return newsFeatureService.insertNewsTheme(newsTheme);
    }

    @PostMapping("/updateNewsThemeById/{id}")
    @ApiOperation(value = "根据ID, 更新新闻主体", response = Result.class)
    public Result updateNewsThemeById(@PathVariable Long id, String title, String data, Integer contypeId, Integer categoryId) throws NoSuchMethodException {
        NewsTheme newsTheme = newsThemeFactory.create(title, data, contypeId, categoryId);
        newsTheme.setId(id);
        newsTheme.setCreateTime(DateUtils.nowDate());
        newsTheme.setUpdateTime(DateUtils.nowDate());
        return newsFeatureService.updateNewsThemeById(newsTheme);
    }

    @PostMapping("/deleteNewsThemeById/{id}")
    @ApiOperation(value = "根据ID, 删除新闻主体", response = Result.class)
    public Result deleteNewsThemeById(@PathVariable Long id) {
        return newsFeatureService.deleteNewsThemeById(id);
    }

    @PostMapping("/insertNewsContype")
    @ApiOperation(value = "插入新闻类型", response = Result.class)
    public Result insertNewsContype(@RequestParam("contypeName") String contypeName) throws NoSuchMethodException {
        NewsContype newsContype = newsContypeFactory.create();
        newsContype.setContypeName(contypeName);
        return newsFeatureService.insertContype(newsContype);
    }

    @PostMapping("/deleteContypeById/{id}")
    @ApiOperation(value = "删除新闻类型", response = Result.class)
    public Result deleteContypeById(@PathVariable("id") Integer id) throws NoSuchMethodException {
        return newsFeatureService.deleteContypeById(id);
    }

    @PostMapping("/updateContypeById/{id}")
    @ApiOperation(value = "更新新闻类型", response = Result.class)
    public Result updateContypeById(@PathVariable("id") Integer id, @RequestParam("contypeName") String contypeName) throws NoSuchMethodException {
        NewsContype newsContype = newsContypeFactory.create();
        newsContype.setId(id);
        newsContype.setContypeName(contypeName);
        return newsFeatureService.updateContypeById(newsContype);
    }

    @PostMapping("/insertNewsCategory")
    @ApiOperation(value = "插入新闻类别", response = Result.class)
    public Result insertNewsCategory(@RequestParam("ContypeId") Integer ContypeId, @RequestParam("contypeName") String contypeName) throws NoSuchMethodException {
        NewsCategory newsCategory = newsCategoryFactory.create();
        newsCategory.setCategoryName(contypeName);
        newsCategory.setContypeId(ContypeId);
        return newsFeatureService.insertNewsCategory(newsCategory);
    }

    @PostMapping("/deleteNewsCategory")
    @ApiOperation(value = "删除新闻类别", response = Result.class)
    public Result deleteNewsCategory(@RequestParam("id") Integer id) throws NoSuchMethodException {
        return newsFeatureService.deleteNewsCategoryById(id);
    }

    @PostMapping("/updateNewsCategory")
    @ApiOperation(value = "更新新闻类别", response = Result.class)
    public Result updateNewsCategory(@RequestParam("ContypeId") Integer ContypeId, @RequestParam("contypeName") String contypeName) throws NoSuchMethodException {
        NewsCategory newsCategory = newsCategoryFactory.create();
        newsCategory.setCategoryName(contypeName);
        newsCategory.setContypeId(ContypeId);
        return newsFeatureService.updateNewsCategory(newsCategory);
    }

}
