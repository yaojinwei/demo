package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

/**
 * 25.获取spring bean
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest2 {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl)processEngine.getProcessEngineConfiguration();

        Object a = processEngineConfiguration.getBeans().get("testA");

        System.out.println(a);

    }
}
