package com.qjw.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
public class CarFactortBean implements FactoryBean<Car>{


    /**
     * 自定义返回对象，
     * @return Car {@link Car}
     */
    @Override
    public Car getObject()  {
        System.out.println("用CarFactortBean初始化Car");
        return new Car();
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
