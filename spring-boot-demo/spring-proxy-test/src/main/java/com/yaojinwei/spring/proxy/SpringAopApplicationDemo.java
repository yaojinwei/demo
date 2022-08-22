package com.yaojinwei.spring.proxy;

import com.alibaba.gts.cache.annotation.EnableAliCaching;
import com.alibaba.gts.cache.extension.switcher.annotation.EnableAliCachingSwitch;

import com.taobao.pandora.boot.PandoraBootstrap;
import com.yaojinwei.spring.proxy.service.LoginService;
import com.yaojinwei.spring.proxy.service.LoginServiceImpl;
import com.yaojinwei.spring.proxy.service.PlainLoginService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 【SpringBoot】@EnableAspectJAutoProxy注解，在SpringBoot中设置proxyTargetClass不生效？
 * https://blog.csdn.net/m0_64491107/article/details/124389042
 */
@SpringBootApplication
@Slf4j
// 指示是否创建基于子类(CGLIB)的代理，而不是创建基于标准Java接口的代理。 默认值是{@code false}。
// 在Spring的环境下设置为true生成的都是cglib的代理，设置为false，默认有接口实现的是jdk的动态代理，没有接口的是cglib代理
// spring.aop.proxy-target-class 为ture或者这里为true都会 当成true
@EnableAspectJAutoProxy(proxyTargetClass = false)
@EnableAliCachingSwitch(dataId = "com.alihealth.nr:alihealth-nr-crm-dynamic.properties")
@EnableAliCaching(proxyTargetClass = true)
@EnableAsync(proxyTargetClass = true)
@Log
public class SpringAopApplicationDemo implements ApplicationRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        PandoraBootstrap.run(args);
        SpringApplication.run(SpringAopApplicationDemo.class, args);
        PandoraBootstrap.markStartupAndWait();
    }

    // 定义切面，或者直接使用@Component注解
    @Bean
    public FooAspect fooAspect() {
        return new FooAspect();
    }

    // 有接口实现
    @Autowired
    LoginService loginService;

    // 无接口实现
    @Autowired
    PlainLoginService plainLoginService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        log.info(loginService.getClass().toString()); // class com.yaojinwei.spring.proxy.service.LoginServiceImpl$$EnhancerBySpringCGLIB$$87c83069
        loginService.login();

        log.info(plainLoginService.getClass().toString()); // class com.yaojinwei.spring.proxy.service.PlainLoginService$$EnhancerBySpringCGLIB$$ad269ebb
        plainLoginService.login();

        log.info("===========" + this.applicationContext.getBean(LoginServiceImpl.class));
        log.info("===========" + this.applicationContext.getBean(PlainLoginService.class));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
