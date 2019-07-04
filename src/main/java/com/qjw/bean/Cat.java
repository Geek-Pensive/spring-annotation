package com.qjw.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
@Component
public class Cat implements InitializingBean,DisposableBean {
    public Cat() {
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("猫的destroy方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("猫的afterPropertiesSet方法");
    }
}
