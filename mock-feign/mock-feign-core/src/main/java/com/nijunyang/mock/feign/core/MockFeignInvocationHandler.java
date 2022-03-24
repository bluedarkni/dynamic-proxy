package com.nijunyang.mock.feign.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;


/**
 * Description: 需要代理的类，执行目标方法
 * Created by nijunyang on 2022/3/8 17:22
 */
public class MockFeignInvocationHandler<T> implements InvocationHandler {

    /**
     * 被代理的目标接口信息
     */
    private Target<T> target;

    /**
     * 接口中的方法信息
     */
    private Map<Method, MethodHandler> dispatch;

    public MockFeignInvocationHandler(Target<T> target, Map<Method, MethodHandler> dispatch) {
        this.target = target;
        this.dispatch = dispatch;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //执行目标方法的代理
        return dispatch.get(method).invoke(args, target);
    }
}
