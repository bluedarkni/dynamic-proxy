package com.nijunyang.mock.feign.core.demo;

import java.lang.reflect.Proxy;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class ProxyFactory<T> {

    //目标对象
    private T target;

    public ProxyFactory(T target) {
        this.target = target;
    }

    //给目标对象生成一个代理
    public T getProxyInstance() {
        //1.ClassLoader loader 目标对象使用的类加载器
        //2.Class<?>[] interfaces 目标对象实现的接口
        //3.InvocationHandler h   事件处理，执行目标对象方法时会触发事件处理器方法, 会把当前执行的目标对象方法作为参数传入
//        return (T) Proxy.newProxyInstance(
//                target.getClass().getClassLoader(),
//                target.getClass().getInterfaces(),
//                (proxy, method, args) -> {
//                    System.out.println("JDK代理");
//                    Object result = method.invoke(target, args);
//                    System.out.println("JDK代理结束");
//                    return result;
//
//                });
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyInvocationHandler(target));
    }
}
