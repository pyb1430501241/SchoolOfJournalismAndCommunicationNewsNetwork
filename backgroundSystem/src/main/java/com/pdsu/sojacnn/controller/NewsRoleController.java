package com.pdsu.sojacnn.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pdsu.sojacnn.bean.NewsRole;
import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsRoleService;
import com.pdsu.sojacnn.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 半梦
 * @since 2021-05-11
 */
@RestController
@RequestMapping("/newsRole")
public class NewsRoleController implements AbstractController {

    @Autowired
    private NewsRoleService newsRoleService;

    @GetMapping("/findAll")
    public Result findAll(@RequestParam(value = "p") Integer p) throws Exception {
        Page<NewsRole> page = new Page<>(p, 8);
        newsRoleService.page(page);
        return PageUtils.defaultPage(Result.ok(), page);
    }

}

