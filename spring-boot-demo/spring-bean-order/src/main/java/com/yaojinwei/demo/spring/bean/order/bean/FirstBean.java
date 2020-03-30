package com.yaojinwei.demo.spring.bean.order.bean;

import org.springframework.stereotype.Component;

/**
 * @author jinwei.yjw
 * @date 2019-09-02 17:10
 */
@Component
public class FirstBean implements Rank {
    @Override
    public String toString() {
        return "FirstBean{}";
    }
}
