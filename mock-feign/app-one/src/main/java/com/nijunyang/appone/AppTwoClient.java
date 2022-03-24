package com.nijunyang.appone;

import com.nijunyang.mock.feign.core.MockFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * Created by nijunyang on 2022/3/10 10:50
 */
@MockFeignClient(name = "app-two", url = "127.0.0.1:8999")
public interface AppTwoClient {


    @GetMapping("app-two")
    Object test(@RequestParam("query") String query);

}
