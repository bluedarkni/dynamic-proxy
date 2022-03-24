package com.nijunyang.mock.feign.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Description: 方法数据信息
 * Created by nijunyang on 2022/3/9 11:22
 */
@Getter
@Setter
public class MethodMetadata {

    /**
     * 返回值类型
     */
    private Type returnType;

    /**
     * 参数注解，用于区分@RequestParam @RequestHeader @RequestBody等
     */
    private Map<Integer, Annotation[]> paramsAnnotationMap;

    /**
     * http方法注解
     */
    private RequestMapping requestMapping;

}
