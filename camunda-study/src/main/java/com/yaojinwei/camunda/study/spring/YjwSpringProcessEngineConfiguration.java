package com.yaojinwei.camunda.study.spring;

import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class YjwSpringProcessEngineConfiguration extends SpringProcessEngineConfiguration {
    @Override
    protected void init() {
        System.out.println("######开始init");
        super.init();
        System.out.println("######结束init");
    }
}
