package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.service.NewsContypeService;
import com.pdsu.sojacnn.utils.PageUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
@RequestMapping("/contype")
@Log4j2
public class NewsContypeController extends AuthenticationController {

    private final NewsContypeService newsContypeService;

    @Autowired
    public NewsContypeController(NewsContypeService newsContypeService) {
        this.newsContypeService = newsContypeService;
    }

    @GetMapping("/findContypeById")
    public Result findContypeById(@RequestParam("id") Integer id) throws Exception {
        NewsContype contype = newsContypeService.getById(id);
        return contype == null ? Result.notFound() :Result.ok().data(DEFAULT_MESSAGE_NAME, contype);
    }

    @PostMapping("/insertContypeById")
    public Result insertContype(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        newsContypeService.save(parseObject(newsContype, NewsContype.class));
        return Result.ok();
    }

    @PostMapping("/deleteContypeById")
    public Result deleteContypeById(@RequestParam("id") Integer id
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        return newsContypeService.removeById(id) ? Result.ok() : Result.fail();
    }

    @PostMapping("/updateContypeById")
    public Result updateContype(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), SUPER_ADMIN);

        newsContypeService.updateById(parseObject(newsContype, NewsContype.class));
        return Result.ok();
    }

    @GetMapping("/findContypeList")
    public Result findContypeList() throws Exception {
        List<NewsContype> list = newsContypeService.list();
        return Objects.isNull(list) ? Result.notFound() : Result.ok().data(DEFAULT_MESSAGE_NAME, list);
    }

}

