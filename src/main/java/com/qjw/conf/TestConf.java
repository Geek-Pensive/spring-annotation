package com.qjw.conf;

import com.qjw.bean.*;
import com.qjw.condition.LinuxCondition;
import com.qjw.condition.WindowsCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.*;

/**
 * @author : qjw
 * @data : 2019/6/6
 */
@Configuration
/**
 * @Import 把用到的资源注册到当容器:bean的id默认是全类名
 *      除了导入普通的class，还可以导入ImportSelector：返回要导入的类的全类名,不要返回null
 *      还可以导入ImportBeanDefinitionRegistrar的实现类，并在实现类中进行注册
 */
@Import({MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
//@Import({Color.class})
public class TestConf {

    /**
     * prototype : getBean时初始化，ioc创建后不会初始化
     * singleton :
     *              ioc创建后初始化，
     *              可以设置懒加载，@Lazy:getBean时初始化，ioc创建后不会初始化
     *
     * @return
     */
    @Scope("prototype")
    @Bean()
    public Person person1(){
        System.out.println("初始化person");
        return new Person("qjw",24);
    }

    /**
     * @ConditionalOnClass :The classes that must be present
     * @ConditionalOnMissingClass : The names of the classes that must not be present.
     * @Conditional 满足条件才会初始化该Bean
     * @return
     */
    @ConditionalOnClass(name = "com.qjw.bean.Green")
    @Conditional(WindowsCondition.class)
    @Bean
    public Person person2(){
        return new Person("zyy",25);
    }


    /**
     * 使用FactoryBean初始化bean，加入到spring容器
     * FactoryBean：以Bean结尾，表示它是一个Bean，不同于普通Bean的是：它是实现了FactoryBean<T>接口的Bean，根据该Bean的Id从BeanFactory中获取的实际上是FactoryBean的getObject()返回的对象，而不是FactoryBean本身， 如果要获取FactoryBean对象，可以在id前面加一个&符号来获取。
     */
    @Bean
    public CarFactortBean carFactortBean (){
        return new CarFactortBean();
    }
}
