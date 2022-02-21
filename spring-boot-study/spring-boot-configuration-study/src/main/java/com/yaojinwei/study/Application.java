package com.yaojinwei.study;

import com.yaojinwei.study.cache.CacheTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }

}
