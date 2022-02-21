package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngineConfiguration;

import java.io.InputStream;

/**
 * 31.手工创建流程引擎3种方式
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest6 {
    public static void main(String[] args) {
        String resource="camunda.cfg.xml";
        InputStream inputStream = ProcessEngineTest6.class.getClassLoader()
                .getResourceAsStream(resource);

        String beanName="processEngineConfiguration";

        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromInputStream(inputStream, beanName);

        System.out.println(processEngineConfigurationFromResource);
    }
}
