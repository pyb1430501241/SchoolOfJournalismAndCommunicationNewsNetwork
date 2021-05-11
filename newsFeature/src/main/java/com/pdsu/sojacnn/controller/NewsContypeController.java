package com.pdsu.sojacnn.controller;


import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsContypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 半梦
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/contype")
@Api(description = "新闻类型管理")
@SuppressWarnings("deprecation")
public class NewsContypeController {

    @Autowired
    private NewsContypeService newsContypeService;

    @GetMapping("/findContypeById")
    @ApiOperation(value = "根据ID, 查询新闻类型", response = Result.class)
    public Result findContypeById(@RequestParam("id") Integer id) {
        return Result.ok().data("item", newsContypeService.getById(id));
    }

    @GetMapping("/insertContypeById")
    @ApiOperation(value = "插入新闻类型", response = Result.class)
    public Result insertContype(@RequestBody NewsContype newsContype) {
        newsContypeService.save(newsContype);
        return Result.ok();
    }
}

