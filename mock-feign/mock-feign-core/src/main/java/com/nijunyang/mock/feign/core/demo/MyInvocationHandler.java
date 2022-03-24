package com.nijunyang.mock.feign.core.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 * Created by nijunyang on 2022/3/8 16:11
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK代理");
        Object result = method.invoke(target, args);
        System.out.println("JDK代理结束");
        return result;
    }
}
