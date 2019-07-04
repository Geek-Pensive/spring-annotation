package com.qjw.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * postProcess!Before!Initialization 、 postProcess!After!Initialization 会在每一个bean的初始化方法：
 *          afterPropertiesSet
 *          PostConstruct
 *          init
 *  方法的前、后调用
 *  源码：
 *      wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *      invokeInitMethods(beanName, wrappedBean, mbd);
 *      wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *
 *       applyBeanPostProcessorsBeforeInitialization方法内容：
 *       for (BeanPostProcessor beanProcessor : getBeanPostProcessors()) {
 *           Object current = beanProcessor.postProcessBeforeInitialization(result, beanName);
 *           if (current == null) {
 *              return result;
 *           }
 *          result = current;
 *       }
 *
 *  应用：1.在任意一个bean中注入ioc容器：实现ApplicationContextAware， 底层是由ApplicationContextAwareProcessor实现
 *        2.@PostContruct、 @PreDestroy注解：底层是由ApplicationContextAwareProcessor实现
 *        3.@Autowired注解：底层是由AutowiredAnnotationBeanPostProcessor实现
 * @author : qjw
 * @data : 2019/6/13
 */
//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcess!Before!Initialization,bean="+beanName+",beanName="+beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcess!After!Initialization,bean="+beanName+",beanName="+beanName);
        return bean;
    }
}
