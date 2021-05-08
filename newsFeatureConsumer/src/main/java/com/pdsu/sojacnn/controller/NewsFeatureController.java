package com.pdsu.sojacnn.controller;

import com.mysql.cj.exceptions.ExceptionInterceptorChain;
import com.mysql.cj.jdbc.Blob;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsFeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "根据ID, 查询新闻类型", response = Result.class)
    @GetMapping("/findContype/{id}")
    public Result findContypeById(@PathVariable Integer id) {
        return newsFeatureService.findContypeById(id);
    }

    @PostMapping("/insertNewsTheme")
    @ApiOperation(value = "插入新闻主体", response = Result.class)
    public Result insertNewsTheme(String title, String data, Integer contypeId, Integer categoryId) {
        NewsTheme newsTheme = new NewsTheme();
        newsTheme.setCategoryId(categoryId);
        newsTheme.setContypeId(contypeId);
        newsTheme.setTitle(title);
        newsTheme.setData(data.getBytes());
        newsTheme.setCreateTime(new Date());
        newsTheme.setUpdateTime(new Date());
        System.out.println(newsTheme);
        return newsFeatureService.insertNewsTheme(newsTheme);
    }

}
