package com.qjw.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Autowired有三种标注位置都可完成依赖注入:都是从容器中获取
 *      1.成员变量
 *      2.构造器，（若Bean只有一个有参构造器，不用标注也可以，需要的参数spring自己会去容器中找）
 *      3.变量的setXxx函数，标注getXx无法注入
 *      [4].@Bean 的方法参数默认加了@Autowired
 * @author : qjw
 * @data : 2019/6/18
 */
@Component
public class Boss {

//    @Autowired
    private Car car;


    /**
     * 不需要@Autowired，
     * @param car
     */
    public Boss(Car car) {
        this.car = car;
        System.out.println("Boss的有参构造器");
    }

    /**
     * Autowired 标注在方法上， Spring容器创建当前对象，就会调用该方法，完成赋值
     * 方法使用的参数，自定义类型的值从ioc容器中获取
     * @param car
     */
//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "BossQJW{" +
                "car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boss boss = (Boss) o;
        return Objects.equals(car, boss.car);
    }

    @Override
    public int hashCode() {

        return Objects.hash(car);
    }
}
