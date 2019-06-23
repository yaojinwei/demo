package com.yaojinwei.demo.spring.bean.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jinwei.yjw
 * @date 2019/1/7 19:37
 */
@Configuration
public class MyConfiguration {

    @Bean(initMethod="myInit", destroyMethod = "myDestroy")
    public Person person(){
        return new Person();
    }
}
