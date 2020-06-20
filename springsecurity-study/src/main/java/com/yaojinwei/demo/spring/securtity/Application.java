package com.yaojinwei.demo.spring.securtity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring Security 入门原理及实战
 * https://www.cnblogs.com/demingblog/p/10874753.html
 *
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@SpringBootApplication
public class Application implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
