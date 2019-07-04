package com.qjw.conf;

import com.qjw.bean.House;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
@Configuration
public class TestBeanLiftConf {

    // 注意：多实例bean，容器不会调用destroyMethod方法
//    @Scope("prototype")
    @Bean(destroyMethod = "destroy",initMethod = "init")
    public House house(){
        return new House();
    }



}
