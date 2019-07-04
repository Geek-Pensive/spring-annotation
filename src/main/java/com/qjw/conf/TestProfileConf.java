package com.qjw.conf;

import com.qjw.bean.MysqlConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Profile:指定组件在哪个环境被激活的情况下才能被注册到容器中，默认系统会激活default环境
 *      不指定：任何环境下都注册这个bean
 * @Profile:也可以写在配置类上，表示在某个环境下，该配置类才生效
 * @author : qjw
 * @data : 2019/6/18
 */
@Profile("default")
@Configuration
public class TestProfileConf {

//    @Profile("dev")
    @Profile("default")
    @Bean
    public MysqlConf mysqlConf(){
        return new MysqlConf();
    }


}
