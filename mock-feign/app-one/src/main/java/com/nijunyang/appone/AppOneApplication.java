package com.nijunyang.appone;

import com.nijunyang.mock.feign.starter.MockEnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MockEnableFeignClients(basePackages = "com.nijunyang.appone")
@SpringBootApplication
public class AppOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppOneApplication.class, args);
    }

}
