package com.nijunyang.mock.feign.core.demo;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class Test {
    public static void main(String[] args)
    {
        TeacherLi teacherLi = new TeacherLi();
        Teacher proxyTeacher = new ProxyFactory<>(teacherLi).getProxyInstance();
        System.out.println(proxyTeacher.getClass());
        proxyTeacher.teach();
    }
}
