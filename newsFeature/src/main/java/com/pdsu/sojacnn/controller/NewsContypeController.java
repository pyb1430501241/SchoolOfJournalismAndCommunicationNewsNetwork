package com.pdsu.sojacnn.controller;


import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsContypeService;
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
public class NewsContypeController {

    @Autowired
    private NewsContypeService newsContypeService;

    @GetMapping("/findContypeById")
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

