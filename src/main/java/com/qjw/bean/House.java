package com.qjw.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
public class House {

    public House() {
        System.out.println("House的构造器");
    }



    private void init(){
        System.out.println("House的init方法");
    }



    private void destroy(){
        System.out.println("House的destory方法");
    }

}
