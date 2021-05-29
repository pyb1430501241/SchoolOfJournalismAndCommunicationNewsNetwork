package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.*;
import com.pdsu.sojacnn.service.NewsContypeService;
import com.pdsu.sojacnn.utils.PageUtils;
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
public class NewsContypeController extends AuthenticationController {

    @Autowired
    private NewsContypeService newsContypeService;

    @GetMapping("/findContypeById")
    public Result findContypeById(@RequestParam("id") Integer id) throws Exception {
        NewsContype contype = newsContypeService.getById(id);
        return contype == null ? Result.notFound() :Result.ok().data(DEFAULT_MESSAGE_NAME, contype);
    }

    @PostMapping("/insertContypeById")
    public Result insertContype(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsContypeService.save(parseObject(newsContype,NewsContype.class));
        return Result.ok();
    }

    @PostMapping("/deleteContypeById")
    public Result deleteContypeById(@RequestParam("id") Integer id
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsContypeService.removeById(id);
        return Result.ok();
    }

    @PostMapping("/updateContypeById")
    public Result updateContype(@RequestParam("newsContype") String newsContype
            , @RequestParam("newsAccountRole") String newsAccountRole) throws Exception {
        authorityJudgment(parseObject(newsAccountRole, NewsAccountRole.class), BASIC_PERSONNEL);

        newsContypeService.updateById(parseObject(newsContype, NewsContype.class));
        return Result.ok();
    }

    @GetMapping("/findContypeList")
    public Result findContypeList() throws Exception {
        //Page<NewsContype> page = new Page<>(p, DEFAULT_PAGE_SIZE);
        //newsContypeService.page(page);
        List<NewsContype> list = newsContypeService.list();
        return Objects.isNull(list) ? Result.notFound() : Result.ok().data(DEFAULT_MESSAGE_NAME, list);
    }

}

