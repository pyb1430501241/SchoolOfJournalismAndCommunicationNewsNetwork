package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.NewsTheme;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.factory.AbstractFactory;
import com.pdsu.sojacnn.factory.NewsContypeFactory;
import com.pdsu.sojacnn.factory.NewsThemeFactory;
import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 半梦
 * @author wl
 * @create 2021-05-08 15:42
 */
@RestController
@Api(description = "游客新闻管理服务")
@SuppressWarnings("deprecation")
public class NewsFeatureController {

    @Autowired
    private NewsFeatureService newsFeatureService;

    @Autowired
    private NewsThemeFactory newsThemeFactory;

    @Autowired
    private NewsContypeFactory newsContypeFactory;

    @ApiOperation(value = "根据ID, 查询新闻类型", response = Result.class)
    @GetMapping("/findContype/{id}")
    public Result findContypeById(@ApiParam(name = "id", value = "类型ID", required = true) @PathVariable Integer id) {
        return newsFeatureService.findContypeById(id);
    }

    @GetMapping("/findNewsTheme/{id}")
    @ApiOperation(value = "根据ID, 查询新闻主体", response = Result.class)
    public Result findNewsThemeById(@ApiParam(name = "id", value = "新闻ID", required = true) @PathVariable Long id) {
        return newsFeatureService.findNewsThemeById(id);
    }

    @GetMapping("/findCategory/{id}")
    @ApiOperation(value = "根据ID, 查询新闻类别", response = Result.class)
    public Result findCategoryById(@ApiParam(name = "id", value = "类别ID", required = true) @PathVariable Integer id) {
        return newsFeatureService.findCategoryById(id);
    }
}
