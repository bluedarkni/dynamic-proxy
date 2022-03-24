package com.nijunyang.mock.feign.core.demo;

/**
 * @author: create by nijunyang
 * @date:2019/10/5
 */
public class TeacherLi implements Teacher {
    @Override
    public void teach() {
        System.out.println("上数学课");
    }
}
