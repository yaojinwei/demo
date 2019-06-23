package com.yaojinwei.demo.spring.bean.lifecycle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *

 @see <img src="https://img-my.csdn.net/uploads/201507/12/1436713414_9075.png"/>
 @see <img src="https://img-my.csdn.net/uploads/201507/12/1436713560_7354.png"/>

 <p>
 这是MyBeanDefinitionRegistryPostProcessor实现类构造器！！
 MyBeanDefinitionRegistryPostProcessor调用postProcessBeanDefinitionRegistry方法
 MyBeanDefinitionRegistryPostProcessor调用postProcessBeanFactory方法

 1、这是BeanFactoryPostProcessor实现类构造器！！
 2、BeanFactoryPostProcessor调用postProcessBeanFactory方法
 3、这是BeanPostProcessor实现类构造器！！
 4、这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！
 5、person：InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法
 6、【构造器】调用Person的构造器实例化
 person：InstantiationAwareBeanPostProcessor调用postProcessAfterInstantiation方法
 7、person：InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法
 8、【注入属性】注入属性phone
 9、【BeanNameAware接口】调用BeanNameAware.setBeanName()
 10、【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
 11、person：BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！
 person：InstantiationAwareBeanPostProcessor调用postProcessBeforeInitializationn方法
 12、【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
 13、【init-method】调用<bean>的init-method属性指定的初始化方法
 14、person：BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！
 15、person：InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法
 16、【DiposibleBean接口】调用DiposibleBean.destory()
 17、【destroy-method】调用<bean>的destroy-method属性指定的初始化方法
 @see <a href="https://blog.csdn.net/bubaxiu/article/details/41415685" >Spring-- Ioc 容器Bean实例化的几种场景</a>
 @see <a href="https://www.jianshu.com/p/e22b9fef311c" >Spring钩子方法和钩子接口的使用详解</a>
 @see <a href="https://blog.csdn.net/qq_36722039/article/details/81589405">spring几种向容器中注册组件的方法</a>
 * @author jinwei.yjw
 * @date 2019/1/7 17:27
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
