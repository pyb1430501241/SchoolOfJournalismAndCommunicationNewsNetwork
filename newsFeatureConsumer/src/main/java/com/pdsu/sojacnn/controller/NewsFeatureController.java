package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.factory.NewsThemeFactory;
import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 半梦
 * @create 2021-05-08 15:42
 */
@RequestMapping("/newsFeature")
@RestController
@Api(description = "游客新闻管理服务")
@SuppressWarnings("deprecation")
public class NewsFeatureController {

    @Autowired
    private NewsFeatureService newsFeatureService;

    @Autowired
    private NewsThemeFactory newsThemeFactory;

    @ApiOperation(value = "根据ID, 查询新闻类型", response = Result.class)
    @GetMapping("/findContype/{id}")
    public Result findContypeById(@PathVariable Integer id) {
        return newsFeatureService.findContypeById(id);
    }

    @PostMapping("/insertNewsTheme")
    @ApiOperation(value = "插入新闻主体", response = Result.class)
    public Result insertNewsTheme(String title, String data, Integer contypeId, Integer categoryId) throws Exception {
        NewsTheme newsTheme = newsThemeFactory.create(title, data, contypeId, categoryId);
        newsTheme.setCreateTime(new Date());
        newsTheme.setUpdateTime(new Date());
        return newsFeatureService.insertNewsTheme(newsTheme);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID, 查询新闻主体", response = Result.class)
    public Result findNewsThemeById(@ApiParam(name = "id", value = "新闻ID", required = true) @PathVariable Long id) {
        return newsFeatureService.findNewsThemeById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID, 更新新闻主体", response = Result.class)
    public Result updateNewsThemeById(@PathVariable Long id, String title, String data, Integer contypeId, Integer categoryId) throws NoSuchMethodException {
        NewsTheme newsTheme = newsThemeFactory.create(title, data, contypeId, categoryId);
        newsTheme.setId(id);
        newsTheme.setCreateTime(DateUtils.nowDate());
        newsTheme.setUpdateTime(DateUtils.nowDate());
        return newsFeatureService.updateNewsThemeById(newsTheme);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据ID, 删除新闻主体", response = Result.class)
    public Result deleteNewsThemeById(@PathVariable Long id) {
        return newsFeatureService.deleteNewsThemeById(id);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "插入新闻类型", response = Result.class)
    public Result insertContypeById(String contypeName) {
        NewsContype newsContype = new NewsContype();
        newsContype.setContypeName(contypeName);
        return newsFeatureService.insertContype(newsContype);
    }


}
