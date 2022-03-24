package com.nijunyang.mock.feign.core;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description:
 * Created by nijunyang on 2022/3/9 10:53
 */
public class MockFeign {

    public <T> T target(Target<T> target) {
        return newInstance(target);
    }

    /**
     * 创建代理实例
     * @param target
     * @param <T>
     * @return
     */
    public <T> T newInstance(Target<T> target) {
        Map<Method, MethodHandler> methodToHandler = parseMethod(target.getType());
        InvocationHandler handler = new MockFeignInvocationHandler<>(target, methodToHandler);
        T proxy = (T) Proxy.newProxyInstance(target.getType().getClassLoader(),
                new Class<?>[]{target.getType()}, handler);
        return proxy;
    }

    /**
     * 解析方法信息
     * @return
     */
    private <T>  Map<Method, MethodHandler> parseMethod(Class<T> clazz) {
        Map<Method, MethodHandler> methodToHandlerMap =  new HashMap<>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            MethodMetadata data = new MethodMetadata();
            data.setReturnType(method.getGenericReturnType());
            Map<Integer, Annotation[]> paramsAnnotationMap = new HashMap<>();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            int index = 0;
            for (Annotation[] annotations : parameterAnnotations) {
                paramsAnnotationMap.put(index++, annotations);
            }
            data.setParamsAnnotationMap(paramsAnnotationMap);
            RequestMapping httpAnnotation = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
            if (httpAnnotation == null) {
                throw new RuntimeException("缺少HTTP请求注解注解!");
            }
            data.setRequestMapping(httpAnnotation);
            MethodHandler methodHandler = new MethodHandler(data);
            methodToHandlerMap.put(method, methodHandler);
        }
        return methodToHandlerMap;
    }
}
