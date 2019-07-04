package com.qjw.conf;

import com.qjw.bean.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : qjw
 * @data : 2019/6/14
 */
@Configuration
public class TestAutowiredConf {

    /**
     * @Autowired 自动注入：
     *  1).默认优先按照类型去容器中找对应组件：applicationContext.getBean(Dog.class)
     *  2).如果找到相同类型的，再将属性的名作为组件id去容器中查找，applicationContext.getBean("dog")
     *  3).用@Qualifier('dog')指定要装配的组件的id，而不是属性名
     *  4).自动装配的对象在容器中一定要存在，否则会报错，可以使用@Autowired(required = false)
     *  5).在注册bean的时候，可以用 @Primary声明该bean，表示成为自动注入时首选bean（可能注册了多个同类型的bean）
     *
     * Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
     *  @Resource:
     *      可以和@Autowired一样实现自动装配功能，默认按照组件名称进行装配的
     *      没有支持@Parmary功能、没有支持@Autowired(required = false)
     *  @Inject:
     *      需要导入javax.inject的包，和@Autowired的功能一样，没有required=false的功能
     * @Autowired:Spring定义的。@Resource、@Inject都是java规范的
     */

    @Autowired
    private Dog dog;

    @Override
    public String toString() {
        return "TestAutowiredConf{" +
                "dog=" + dog +
                '}';
    }

}
