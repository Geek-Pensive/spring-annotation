package com.qjw.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * AOP：
 *  指在程序运行期间动态的将某段代码切入到指定方法指定的位置，并运行的编程方式
 *  原理【动态代理】
 *
 *  1.导入aop模块：spring-context、spring-aspect
 *  2.定义一个业务逻辑类(userService)
 *  3.定义一个日志切面类(LogAspect)
 *         定义通知方法：
 *              前置通知(@Before)：
 *              后置通知(@After)：
 *              返回通知(@AfterReturning)：
 *              异常通知(@AfterThrowing)：
 *              环绕通知(@Around)：
 *  4.定义切点(pointCut)，并引用
 *  5.将切点类和切面类加入Spring容器
 *  6.声明切面类@Aspect
 *  7.开启切面代理@EnableAspectJAutoProxy
 * @author : qjw
 * @data : 2019/6/18
 */
/**
 * 源码分析：【看给容器中注册了什么组件，这个组件什么时候工作，这个组件的功能是什么】
 *  1.@EnableAspectJAutoProxy是什么？
 *      @Import(AspectJAutoProxyRegistrar.class)
 *      class AspectJAutoProxyRegistrar implements ImportBeanDefinitionRegistrar
 *      给容器注册 registry.registerBeanDefinition("org.springframework.aop.config.internalAutoProxyCreator", beanDefinition)  beanDefinition是 AnnotationAwareAspectJAutoProxyCreator
 *  2.AnnotationAwareAspectJAutoProxyCreator： extends AspectJAwareAdvisorAutoProxyCreator extends AbstractAdvisorAutoProxyCreator extends AbstractAutoProxyCreator
 *      AbstractAutoProxyCreator implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware。关注后置处理器（在bean初始化后做的事），自动装配BeanFactory
 *
 *      AbstractAutoProxyCreator.setBeanFactory()
 *      AbstractAutoProxyCreator 有后置处理器
 *
 *      AbstractAdvisorAutoProxyCreator.setBeanFactory()->initBeanFactory()
 *
 *      AnnotationAwareAspectJAutoProxyCreator.initBeanFactory()
 *
 *  整体流程：
 *      1.传入配置类，创建ioc容器(我没有根据特定的配置类创建ioc容器，默认扫描所有的配置类) :
 *      2.AnnotationConfigApplicationContext类的有参构造器，register（注册）、refresh(刷新)容器
 *      3.refresh -> registerBeanPostProcessors(beanFactory) -> PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
 *      4.registerBeanPostProcessors具体流程：
 *          获取ioc容器定义了需要创建的PostProcess:String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
 *          给容器加别的PostProcess
 *          首先, 优先注册实现了PriorityOrdered的BeanPostProcessors
 *          Next, register the BeanPostProcessors that implement Ordered.
 *          Now, register all regular BeanPostProcessors.
 *          注册BeanPostProcessor，实际上就是创建BeanPostProcess对象，保存在容器中
 *
 */

/**
 * 日志切面类
 *
 * @author : qjw
 * @data : 2019/6/18
 */
@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class LogAspects {

    // 抽取公共的切入点表达式
    // 1.本类引用 @Before("pointCut()")
    // 2.其他类引用 @After("com.qjw.aop.LogAspects.pointCut()")
    @Pointcut("execution(public int com.qjw.aop.UserService.*(..))")
    public void pointCut() {}

//        @Before("public int com.qjw.aop.UserService.*(...)")
    @Before("pointCut()")
    private void logMethodStart(JoinPoint joinPoint) {
        log.info("joinPoint = {},methodName = {} start. parameters:{}",joinPoint,joinPoint.getSignature().getName(), Arrays.asList(joinPoint.getArgs()));
    }

    @After("com.qjw.aop.LogAspects.pointCut()")
    private void logMethodEnd(JoinPoint joinPoint) {
        log.info("methodName = {} end. parameters:{}",joinPoint.getSignature().getName(),Arrays.asList(joinPoint.getArgs()));
    }

    // 注意，一定把JoinPoint joinPoint写在前面，否则报错
    @AfterReturning(value = "pointCut()",returning = "result")
    private void logMethodReturn(JoinPoint joinPoint,Object result) {
        log.info("methodName = {} return. parameters:{},result:{}",joinPoint.getSignature().getName(),Arrays.asList(joinPoint.getArgs()),result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    private void logMethodExcept(JoinPoint joinPoint,Exception exception) {
        log.error("methodName = {} end. parameters:{},throwable:{}",joinPoint.getSignature().getName(),Arrays.asList(joinPoint.getArgs()),exception.getMessage());
    }

}
