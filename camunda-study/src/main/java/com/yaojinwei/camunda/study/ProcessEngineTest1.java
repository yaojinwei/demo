package com.yaojinwei.camunda.study;

import org.camunda.bpm.engine.*;

/**
 * 20.获取引擎类及服务类
 * deployment.lock	0	1
 * history.cleanup.job.lock	0	1
 * historyLevel	2	1
 * next.dbid	1	1
 * schema.history	create(fox)	1
 * schema.version	fox	1
 * startup.lock	0	1
 *
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ProcessEngineTest1 {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine.getName());
        System.out.println("流程引擎配置类:" + processEngine.getProcessEngineConfiguration());

        AuthorizationService authorizationService = processEngine.getAuthorizationService();
        System.out.println("authorizationService:" + authorizationService);

        //CMMN引擎相关的
        CaseService caseService = processEngine.getCaseService();
        System.out.println("caseService:" + caseService);

        //DMN引擎相关的
        DecisionService decisionService = processEngine.getDecisionService();
        System.out.println("decisionService:" + decisionService);
        ExternalTaskService externalTaskService = processEngine.getExternalTaskService();
        System.out.println("externalTaskService:" + externalTaskService);
        FilterService filterService = processEngine.getFilterService();
        System.out.println("filterService:" + filterService);
        FormService formService = processEngine.getFormService();
        System.out.println("formService:" + formService);


//        defaultProcessEngine.get

    }
}
