package com.qjw.condition;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 判断当前是否为window的条件bean，用于@Conditional注解
 *
 * @author : qjw
 * @data : 2019/6/10
 */
public class WindowsCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");
        BeanDefinitionRegistry registry = context.getRegistry();
        // 判断是Windows系统 且 已经注册了person1
        if (os.contains("Windows") && registry.containsBeanDefinition("person1")) {
            return true;
        }else{
//            registry.registerBeanDefinition("person1");
        }
        return false;
    }
}
