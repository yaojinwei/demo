package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngineConfiguration;

/**
 * 31.手工创建流程引擎3种方式
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest5 {
    public static void main(String[] args) {
        String resource="camunda.cfg.xml";
        String beanName="processEngineConfiguration";

        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(resource, beanName);

        System.out.println(processEngineConfigurationFromResource);
    }
}
