package com.nijunyang.appone;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2022/3/10 10:49
 */
@RestController
@RequestMapping("app-one")
public class Controller {

    @Resource
    private AppTwoClient appTwoClient;

    @GetMapping
    public Object test(String query) {
        return appTwoClient.test(query);
    }
}
