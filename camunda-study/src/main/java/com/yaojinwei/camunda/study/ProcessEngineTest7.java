package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;

/**
 * 32.标准版流程引擎类扩展思路
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest7 {
    public static void main(String[] args) {
        String resource="activiti2.cfg.xml";
        String beanName="processEngineConfiguration2";

        ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource(resource, beanName);

        System.out.println(processEngineConfigurationFromResource);

        ProcessEngine processEngine = processEngineConfigurationFromResource.buildProcessEngine();
        System.out.println(processEngine);
    }
}
