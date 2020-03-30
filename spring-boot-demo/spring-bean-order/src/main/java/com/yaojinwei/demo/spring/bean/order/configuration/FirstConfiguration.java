package com.yaojinwei.demo.spring.bean.order.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author jinwei.yjw
 * @date 2019-09-02 17:17
 */
public class FirstConfiguration {
    @Bean
    @ConditionalOnMissingBean(Fank.class)
    public FirstFank firstFank(){
        return new FirstFank();
    }
}
