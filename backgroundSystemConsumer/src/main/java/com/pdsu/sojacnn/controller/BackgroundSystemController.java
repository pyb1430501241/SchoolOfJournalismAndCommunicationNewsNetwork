package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.exception.NewsException;
import com.pdsu.sojacnn.factory.NewsCategoryFactory;
import com.pdsu.sojacnn.factory.NewsContypeFactory;
import com.pdsu.sojacnn.factory.NewsThemeFactory;
import com.pdsu.sojacnn.service.BackgroundService;
import com.pdsu.sojacnn.service.NewsFeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class BackgroundSystemController implements AbstractController {

    private final NewsFeatureService newsFeatureService;

    private final NewsThemeFactory newsThemeFactory;

    private final NewsContypeFactory newsContypeFactory;

    private final NewsCategoryFactory newsCategoryFactory;

    private final BackgroundService backgroundService;

    @Autowired
    public BackgroundSystemController(NewsFeatureService newsFeatureService, NewsThemeFactory newsThemeFactory, NewsContypeFactory newsContypeFactory, NewsCategoryFactory newsCategoryFactory, BackgroundService backgroundService) {
        this.newsFeatureService = newsFeatureService;
        this.newsThemeFactory = newsThemeFactory;
        this.newsContypeFactory = newsContypeFactory;
        this.newsCategoryFactory = newsCategoryFactory;
        this.backgroundService = backgroundService;
    }

    /**
     * 拦截器已预先判空, 故<code>request.getHeader</code>不可能返回 null
     */
    @NonNull
    private NewsAccountRole accountAuthorization(@NonNull HttpServletRequest request) throws NewsException {
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
        return newsFeatureService.updateNewsThemeById(parseJson(newsTheme)
                , parseJson(accountAuthorization(request)));
    }

    @PostMapping("/deleteNewsTheme/{id}")
    @ApiOperation(value = "根据ID, 删除新闻主体", response = Result.class)
    public Result deleteNewsThemeById(@PathVariable Long id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteNewsThemeById(id, parseJson(accountAuthorization(request)));
    }

    @PostMapping("/saveNewsContype")
    @ApiOperation(value = "插入新闻类型", response = Result.class)
    public Result insertNewsContype(@RequestParam("contypeName") String contypeName, HttpServletRequest request) throws Exception {
        NewsContype newsContype = newsContypeFactory.create(contypeName);
        return newsFeatureService.insertContype(parseJson(newsContype), parseJson(accountAuthorization(request)));
    }

    @PostMapping("/deleteContype/{id}")
    @ApiOperation(value = "删除新闻类型", response = Result.class)
    public Result deleteContypeById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteContypeById(id, parseJson(accountAuthorization(request)));
    }

    @PostMapping("/updateContype/{id}")
    @ApiOperation(value = "更新新闻类型", response = Result.class)
    public Result updateContypeById(@PathVariable("id") Integer id, @RequestParam("contypeName") String contypeName, HttpServletRequest request) throws Exception {
        NewsContype newsContype = newsContypeFactory.create(id, contypeName);
        return newsFeatureService.updateContypeById(parseJson(newsContype), parseJson(accountAuthorization(request)));
    }

    @PostMapping("/saveNewsCategory")
    @ApiOperation(value = "插入新闻类别", response = Result.class)
    public Result insertNewsCategory(@RequestParam("contypeId") Integer contypeId
            , @RequestParam("categoryName") String categoryName, HttpServletRequest request) throws Exception {
        NewsCategory newsCategory = newsCategoryFactory.create(categoryName,contypeId);
        return newsFeatureService.insertNewsCategory(parseJson(newsCategory), parseJson(accountAuthorization(request)));
    }

    @PostMapping("/deleteNewsCategory")
    @ApiOperation(value = "删除新闻类别", response = Result.class)
    public Result deleteNewsCategory(@RequestParam("id") Integer id, HttpServletRequest request) throws Exception {
        return newsFeatureService.deleteNewsCategoryById(id, parseJson(accountAuthorization(request)));
    }

    @PostMapping("/updateNewsCategory")
    @ApiOperation(value = "更新新闻类别", response = Result.class)
    public Result updateNewsCategory(@RequestParam("id") Integer id, @RequestParam("contypeId") Integer contypeId
            , @RequestParam("categoryName") String categoryName, HttpServletRequest request) throws Exception {
        NewsCategory newsCategory = newsCategoryFactory.create(id,categoryName,contypeId);
        return newsFeatureService.updateNewsCategory(parseJson(newsCategory), parseJson(accountAuthorization(request)));
    }

}
