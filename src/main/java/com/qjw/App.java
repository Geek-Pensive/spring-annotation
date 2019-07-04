package com.qjw;

import com.qjw.aop.UserService;
import com.qjw.bean.Boss;
import com.qjw.bean.Dog;
import com.qjw.bean.KylinConf;
import com.qjw.conf.TestAutowiredConf;
import com.qjw.conf.TestPropertyConf;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

/**
 * SpringBootApplication 底层自动开启了@ComponentScan
 *
 * @author : qjw
 * @data : 2019/6/6
 */
/*@ComponentScans(value = {
        @ComponentScan(
                value = "com.qjw",
                includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
                )
    }
)*/
@SpringBootApplication
/**
 * @MapperScan 是mybatis实现的注解：Note that only interfaces，with at least one method will be registered; concrete classes will be ignored.
 * {@link MapperScan} 底层是：MapperScannerRegistrar：scanner.doScan(StringUtils.toStringArray(basePackages));
 */
//@MapperScan
@Slf4j
/**
 * 1.@EnableTransactionManagement：开启基于注解的事务管理方法（会默认初始化事务管理器 ）
 * 2.给方法上加上@Transcational
 *  @EnableTransactionManagement的原理：
 *      ①import TransactionManagementConfigurationSelector
 *      ②TransactionManagementConfigurationSelector会导入两个组件（因为adviceMode 为 Proxy）
 *          AutoProxyRegistrar：
 *          ProxyTransactionManagementConfiguration
 *
 */
@EnableTransactionManagement
public class App<S> implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String[] beans = applicationContext.getBeanDefinitionNames();
        for (String bean : beans) {
//            System.out.println(bean);
        }

        Object person1 = applicationContext.getBean("person1");
        Object person2 = applicationContext.getBean("person2");
        // Scope默认为Singleton，则为true
        System.out.println(person2);

        Object carFactortBean1 = applicationContext.getBean("carFactortBean");
        Object carFactortBean2 = applicationContext.getBean("carFactortBean");
        System.out.println("beanFactory的类型：" + carFactortBean1.getClass());
        System.out.println(carFactortBean1 == carFactortBean2);

        Object carFactortBean3 = applicationContext.getBean("&carFactortBean");
        System.out.println("&beanFactory的类型：" + carFactortBean3.getClass());

        Object mysqlConf = applicationContext.getBean("mysqlConf");
        System.out.println("mysqlConf:" + mysqlConf);
        Object kylinConf = applicationContext.getBean("kylinConf");
        System.out.println("kylinConf:" + kylinConf);

        Object rainBow = applicationContext.getBean("rainBow");
        System.out.println("rainBow:" + rainBow);

        TestAutowiredConf autowiredConf = applicationContext.getBean(TestAutowiredConf.class);
        Dog dog = applicationContext.getBean(Dog.class);
        System.out.println(autowiredConf);
        System.out.println(dog);

        Boss boss = applicationContext.getBean(Boss.class);
        System.out.println("boss:" + boss);
        log.info("boss={}",boss);
        System.out.println("----------------aop--------------");
        UserService userService = applicationContext.getBean(UserService.class);
        System.out.println(userService.div(7, 2));
        System.out.println("----------------aop--------------");
        /**
         * 泛型测试
         */
        //获取string类型
        List<String> array = new ArrayList<String>();
        array.add("test");
        array.add("doub");
        String str = getFirst1(array);
        System.out.println(str);

        //获取nums类型
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(12);
        nums.add(13);
        Integer first1 = getFirst1(nums);
        System.out.println(first1);

        String first2 = new App<String>().getFirst2(array);
        System.out.println(first2);

        Thread.sleep(1000);

    }

    private <T> T getFirst1(List<T> list) {
        return list.get(0);
    }

    /**
     * 返回值，直接写T表示限制参数的类型，这种方法一般多用于共同操作一个类对象
     *
     * @param data 这个只能传递T类型的数据
     * @return
     */
    private S getFirst2(List<S> data) {
        return data.get(0);
    }
}
