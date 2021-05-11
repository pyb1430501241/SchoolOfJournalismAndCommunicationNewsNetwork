package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.BackgroundService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 半梦
 * @create 2021-05-11 20:44
 */
@SuppressWarnings("deprecation")
@RestController
@Api(description = "后台管理")
public class BackgroundSystemController {

    @Autowired
    private BackgroundService backgroundService;

    @GetMapping("/findRoles")
    @ApiOperation(value = "查询所有的角色", response = Result.class)
    public Result findNewsRoles() {
        return backgroundService.findNewsRoles();
    }

}
