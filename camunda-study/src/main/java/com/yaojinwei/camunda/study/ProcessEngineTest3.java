package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineInfo;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;

/**
 * 29.流程引擎管理类获取集合演示
 * 30.流程引擎插件原理分析及使用
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest3 {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl)processEngine.getProcessEngineConfiguration();

        System.out.println("#####"+processEngine);
        ProcessEngine processEngineDefault = ProcessEngines.getProcessEngine("default");

        System.out.println("#####"+processEngineDefault);

        ProcessEngineInfo processEngineInfo = ProcessEngines.getProcessEngineInfo("default");
        System.out.println(processEngineInfo);
        System.out.println(processEngineInfo.getName()+",");
        System.out.println(processEngineInfo.getResourceUrl()+",");
        System.out.println(processEngineInfo.getException()+",");

    }
}
