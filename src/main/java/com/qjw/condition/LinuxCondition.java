package com.qjw.condition;

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
public class LinuxCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String os = environment.getProperty("os.name");
        if (os.contains("Linux")) {
            return true;
        }
        return false;
    }
}
