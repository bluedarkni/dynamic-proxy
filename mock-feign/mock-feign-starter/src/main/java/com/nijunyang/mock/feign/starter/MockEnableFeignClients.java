package com.nijunyang.mock.feign.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 开启feign注解
 * Created by nijunyang on 2022/3/9 15:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ClientsRegistrar.class)
public @interface MockEnableFeignClients {

    /**
     * client所在的包，扫描此包加载client
     * @return
     */
    String[] basePackages() default {};
}
