package com.yaojinwei.study.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@SpringBootApplication
@MapperScan("com.yaojinwei.study.es.test.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
