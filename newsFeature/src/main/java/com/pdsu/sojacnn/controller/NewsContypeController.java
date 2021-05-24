package com.pdsu.sojacnn.controller;


import com.pdsu.sojacnn.bean.NewsAccountRole;
import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.bean.NewsTheme;
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

}

