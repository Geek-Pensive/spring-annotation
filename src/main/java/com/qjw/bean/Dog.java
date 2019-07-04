package com.qjw.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
@Component
public class Dog {

    public Dog() {
        System.out.println("Dog的构造器");
    }

    @PostConstruct
    public void testPostConstruct(){
        System.out.println("Dog的PostConstruct方法");
    }

    @PreDestroy
    public void testPreDestroy(){
        System.out.println("Dog的testPreDestroy方法");
    }


}
