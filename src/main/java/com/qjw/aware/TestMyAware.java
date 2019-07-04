package com.qjw.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 自定义组件 想要使用Spring容器底层的一些组件(ApplicationContext，BeanFactory)
 *      只需要实现xxxAware，在创建对象的的，spring会自动调用其Override的方法：注入相关逐渐
 * 底层原理：ApplicationContextAwareProcessor
 * @author : qjw
 * @data : 2019/6/18
 */
@Component
public class TestMyAware implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {
    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String name) {
        System.out.println("为TestMyAware setBeanName,name=" + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的applicationContext:" + applicationContext);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String s = resolver.resolveStringValue("Hello ${os.name},#{20*5}");
        System.out.println("EmbeddedValueResolverAware用传入的解析器，解析后的数据：" + s);
    }
}
