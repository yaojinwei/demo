package com.yaojinwei.demo.spring.bean.order;

import com.yaojinwei.demo.spring.bean.order.bean.Results;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 测试bean的加载顺序
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        Results results = applicationContext.getBean(Results.class);
        System.out.println(results);
    }
}