package com.nijunyang.apptwo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * Created by nijunyang on 2022/3/10 10:49
 */
@RestController
@RequestMapping("app-two")
public class Controller {


    @GetMapping
    public String test(String query) {
        return "我是app-two--->>>>" + query;
    }
}
