package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.cfg.ProcessEnginePlugin;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class YjwProcessEnginePlugin implements ProcessEnginePlugin {
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        System.out.println("preInit##############" + processEngineConfiguration);
    }

    public void postInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        System.out.println("postInit##############" + processEngineConfiguration);
    }

    public void postProcessEngineBuild(ProcessEngine processEngine) {
        System.out.println("postProcessEngineBuild##############" + processEngine);
    }
}
