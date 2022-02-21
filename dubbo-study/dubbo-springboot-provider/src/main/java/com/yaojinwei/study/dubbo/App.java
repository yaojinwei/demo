package com.yaojinwei.study.dubbo;

import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@EnableAutoConfiguration
@ComponentScan("com.yaojinwei.study.dubbo")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }


}
