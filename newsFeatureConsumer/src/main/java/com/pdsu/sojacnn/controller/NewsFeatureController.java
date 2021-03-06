package com.pdsu.sojacnn.controller;

import com.pdsu.sojacnn.bean.Result;
import com.pdsu.sojacnn.service.NewsFeatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
public class NewsFeatureController implements AbstractController {

    private final NewsFeatureService newsFeatureService;

    @Autowired
    public NewsFeatureController(NewsFeatureService newsFeatureService) {
        this.newsFeatureService = newsFeatureService;
    }

    @ApiOperation(value = "根据ID, 查询新闻类型", response = Result.class)
    @GetMapping("/findContype/{id}")
    public Result findContypeById(@ApiParam(name = "id", value = "类型ID", required = true)
                                      @PathVariable Integer id) throws Exception {
        return newsFeatureService.findContypeById(id);
    }

    @GetMapping("/findNewsTheme/{id}")
    @ApiOperation(value = "根据ID, 查询新闻主体", response = Result.class)
    public Result findNewsThemeById(@ApiParam(name = "id", value = "新闻ID", required = true)
                                        @PathVariable Long id) throws Exception {
        return newsFeatureService.findNewsThemeById(id);
    }

    @GetMapping("/findNewsByTypeAndCategory")
    @ApiOperation(value = "根据类型类别查询一类新闻", response = Result.class)
    public Result findNewsThemesByTypeIdAndCategoryId(@RequestParam("contypeId") Integer typeId
            , @RequestParam Integer categoryId
            , @RequestParam(value = "p", defaultValue = "1") Integer p) throws Exception {
        return newsFeatureService.findNewsThemesByTypeIdAndCategoryId(typeId, categoryId, p);
    }

    @GetMapping("/findCategory/{id}")
    @ApiOperation(value = "根据ID, 查询新闻类别", response = Result.class)
    public Result findCategoryById(@ApiParam(name = "id", value = "类别ID", required = true) @PathVariable Integer id) throws Exception {
        return newsFeatureService.findCategoryById(id);
    }

    @GetMapping("/findContype")
    @ApiOperation(value = "查询所有新闻类型", response = Result.class)
    public Result findContypeList() throws Exception {
        return newsFeatureService.findContypeList();
    }

    @GetMapping("/findCategoryByContypeId")
    @ApiOperation(value = "根据新闻类型查询新闻类别", response = Result.class)
    public Result findCategoryIdByContypeId(@RequestParam("contypeId") Integer contypeId
            , @RequestParam(value = "p", defaultValue = "1") Integer p) throws Exception {
        return newsFeatureService.findCategoryIdByContypeId(contypeId, p);
    }

}
