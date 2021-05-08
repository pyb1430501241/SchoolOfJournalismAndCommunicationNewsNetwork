package com.pdsu.sojacnn.test;

import com.pdsu.sojacnn.NewsFeatureRun;
import com.pdsu.sojacnn.bean.NewsContype;
import com.pdsu.sojacnn.service.NewsContypeService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 半梦
 * @create 2021-05-07 18:05
 */
@SpringBootTest(classes = {NewsFeatureRun.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {

    @Autowired
    private NewsContypeService contypeService;

    @org.junit.Test
    public void test() {
        contypeService.save(new NewsContype(null, "学术社团", null));
    }


}
