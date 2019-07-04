package com.qjw.conf;

import com.qjw.bean.KylinConf;
import com.qjw.bean.MysqlConf;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
@Configuration
/**
 * 属性从外部配置文件注入
 * 1.使用PropertySource读取外部配置文件中的k/v，保存到运行的环境变量中(ioc的environment中)，可以使用@Value或者
 * 2.在配置类中用@Bean+@ConfigurationProperties(prefix = "kylin")，注意要将配置文件的路径写在@PropertySourc中
 * 3.在配置类中开启@EnableConfigurationProperties({KylinConf.class})，在KylinConf类上用@Component+@ConfigurationProperties(prefix = "kylin")
 */
@EnableConfigurationProperties({KylinConf.class})
@PropertySource(value = {"classpath:/mysql.yml","classpath:/kylin.yml"})
public class TestPropertyConf {

//    @Bean
    public MysqlConf mysqlConf() {
        return new MysqlConf();
    }

   /* @Bean
    @ConfigurationProperties(prefix = "kylin")
    public KylinConf kylinConf() {
        return new KylinConf();
    }*/

}
