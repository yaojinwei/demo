package com.yaojinwei.camunda.study.spring;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 35.Spring风格流程引擎类扩展
 *
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class SpringProcessEngineTest2 {
    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        System.out.println(processEngine);

        ApplicationContext ac = new ClassPathXmlApplicationContext("activiti-context2.xml");

        ProcessEngineConfiguration processEngineConfiguration = ac.getBean(ProcessEngineConfiguration.class);

//        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
//                .createProcessEngineConfigurationFromResource("activiti-context.xml");

//        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        ProcessEngine processEngine = ac.getBean(ProcessEngine.class);
        System.out.println(processEngine);


    }
}
