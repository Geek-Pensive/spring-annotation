package com.qjw.bean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author : qjw
 * @data : 2019/6/13
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 手工注册bean到容器：registry.registerBeanDefinition();
     * @param importingClassMetadata 当前类的注解信息
     * @param registry BeanDefinition注册类：
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean person = registry.containsBeanDefinition("person1");
        if (person){
            registry.registerBeanDefinition("rainBow",new RootBeanDefinition(RainBow.class));
        }


//        System.out.println("registerBeanDefinitions().importingClassMetadata="+importingClassMetadata+"BeanDefinitionRegistry="+registry);
    }

}
class RainBow {

}
