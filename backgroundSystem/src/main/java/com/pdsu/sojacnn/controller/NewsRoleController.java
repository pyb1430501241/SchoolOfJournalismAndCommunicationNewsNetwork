package com.pdsu.sojacnn.controller;


import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
public class NewsRoleController {

    @Autowired
    private NewsRoleService newsRoleService;

    @GetMapping("/findAll")
    public Result findAll() {
        return Result.ok().data("items", newsRoleService.list());
    }

}

