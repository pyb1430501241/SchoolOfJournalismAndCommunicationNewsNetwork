package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.factory.NewsCategoryFactory;
import com.pdsu.sojacnn.factory.NewsContypeFactory;
import com.pdsu.sojacnn.factory.NewsThemeFactory;
import com.pdsu.sojacnn.service.BackgroundService;
import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 半梦
 * @author wl
 * @create 2021-05-11 20:44
 */
@SuppressWarnings("deprecation")
@RestController
@Api(description = "后台管理")
public class BackgroundSystemController implements AbstractController {

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

    // 拦截器已预先判空, 故不可能返回 null
    @Nullable
    private NewsAccountRole accountAuthorization(@NonNull HttpServletRequest request) {
        return parseObject(request.getHeader(ACCOUNT_SESSION_FLAG), NewsAccountRole.class);
    }

    @GetMapping("/findRoles")
    @ApiOperation(value = "查询所有的角色", response = Result.class)
    public Result findNewsRoles(@RequestParam(value = "p", defaultValue = "1")Integer p, HttpServletRequest request) throws Exception {
        return backgroundService.findNewsRoles(p, accountAuthorization(request));
    }

    @PostMapping("/saveNewsTheme")
    @ApiOperation(value = "插入新闻主体", response = Result.class)
    public Result insertNewsTheme(String title, String data, Integer contypeId, Integer categoryId, HttpServletRequest request) throws Exception {
        NewsTheme newsTheme = newsThemeFactory.create(title, data, contypeId, categoryId, true, true);

        return newsFeatureService.insertNewsTheme(parseJson(newsTheme)
                , parseJson(accountAuthorization(request)));
    }

    @PostMapping("/updateNewsTheme/{id}")
    @ApiOperation(value = "根据ID, 更新新闻主体", response = Result.class)
    public Result updateNewsThemeById(@PathVariable Long id, String title, String data
            , Integer contypeId, Integer categoryId, HttpServletRequest request) throws Exception {
        NewsTheme newsTheme = newsThemeFactory.create(id, title, data, contypeId, categoryId, false, true);
        return newsFeatureService.updateNewsThemeById(newsTheme);
    }

    @PostMapping("/deleteNewsTheme/{id}")
    @ApiOperation(value = "根据ID, 删除新闻主体", response = Result.class)
    public Result deleteNewsThemeById(@PathVariable Long id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteNewsThemeById(id);
    }

    @PostMapping("/saveNewsContype")
    @ApiOperation(value = "插入新闻类型", response = Result.class)
    public Result insertNewsContype(@RequestParam("contypeName") String contypeName, HttpServletRequest request) throws Exception {
        NewsContype newsContype = newsContypeFactory.create(contypeName);
        return newsFeatureService.insertContype(newsContype);
    }

    @PostMapping("/deleteContype/{id}")
    @ApiOperation(value = "删除新闻类型", response = Result.class)
    public Result deleteContypeById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteContypeById(id);
    }

    @PostMapping("/updateContype/{id}")
    @ApiOperation(value = "更新新闻类型", response = Result.class)
    public Result updateContypeById(@PathVariable("id") Integer id, @RequestParam("contypeName") String contypeName, HttpServletRequest request) throws Exception {
        NewsContype newsContype = newsContypeFactory.create(id, contypeName);
        return newsFeatureService.updateContypeById(newsContype);
    }

    @PostMapping("/saveNewsCategory")
    @ApiOperation(value = "插入新闻类别", response = Result.class)
    public Result insertNewsCategory(@RequestParam("contypeId") Integer contypeId
            , @RequestParam("categoryName") String categoryName, HttpServletRequest request) throws Exception {
        NewsCategory newsCategory = newsCategoryFactory.create();
        newsCategory.setCategoryName(categoryName);
        newsCategory.setContypeId(contypeId);
        return newsFeatureService.insertNewsCategory(newsCategory);
    }

    @PostMapping("/deleteNewsCategory")
    @ApiOperation(value = "删除新闻类别", response = Result.class)
    public Result deleteNewsCategory(@RequestParam("id") Integer id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteNewsCategoryById(id);
    }

    @PostMapping("/updateNewsCategory")
    @ApiOperation(value = "更新新闻类别", response = Result.class)
    public Result updateNewsCategory(@RequestParam("id") Integer id, @RequestParam("contypeId") Integer contypeId
            , @RequestParam("categoryName") String categoryName, HttpServletRequest request) throws Exception {
        NewsCategory newsCategory = newsCategoryFactory.create(id,categoryName,contypeId);
        return newsFeatureService.updateNewsCategory(newsCategory);
    }

}
