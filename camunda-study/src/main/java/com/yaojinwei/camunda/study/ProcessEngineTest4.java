package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.history.HistoryLevel;

/**
 * 31.手工创建流程引擎3种方式
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest4 {
    public static void main(String[] args) {
        ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl)ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/camunda?characterEncoding=UTF-8");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        processEngineConfiguration.setHistoryLevel(HistoryLevel.HISTORY_LEVEL_AUDIT);

        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
    }
}
