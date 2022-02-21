package com.yaojinwei.camunda.study.ch9;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class RuntimeServiceTest {
    public IdentityService identityService;
    AuthorizationService authorizationService;
    ProcessEngineConfigurationImpl processEngineConfiguration;
    ManagementService managementService;
    RepositoryService repositoryService;
    RuntimeService runtimeService;
    TaskService taskService;
    HistoryService historyService;

    @Before
    public void init() {
        processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("camunda.ch9.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        identityService = processEngine.getIdentityService();

        authorizationService = processEngine.getAuthorizationService();
        managementService = processEngine.getManagementService();
        repositoryService = processEngine.getRepositoryService();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
        historyService = processEngine.getHistoryService();
        System.out.println(identityService);
    }

    @Test
    public void addClasspathResource() {
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("leave2")
                .source("本地测试")
//                .tenantId("A系统")
                //.nameFromDeployment("")
                .addClasspathResource("leave2.bpmn")
                .deploy();

        System.out.println(deployment);

    }

    /**
     * Result 0:	insert into ACT_HI_TASKINST ( ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, CASE_DEF_KEY_, CASE_DEF_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, ACT_INST_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, OWNER_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TASK_DEF_KEY_, PRIORITY_, DUE_DATE_, FOLLOW_UP_DATE_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 1:	insert into ACT_HI_PROCINST ( ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_KEY_, PROC_DEF_ID_, START_TIME_, END_TIME_, REMOVAL_TIME_, DURATION_, START_USER_ID_, START_ACT_ID_, END_ACT_ID_, SUPER_PROCESS_INSTANCE_ID_, ROOT_PROC_INST_ID_, SUPER_CASE_INSTANCE_ID_, CASE_INST_ID_, DELETE_REASON_, TENANT_ID_, STATE_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 2:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 3:	insert into ACT_HI_ACTINST ( ID_, PARENT_ACT_INST_ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, CALL_CASE_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, ACT_INST_STATE_, SEQUENCE_COUNTER_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1, 1]
     * Result 4:	insert into ACT_RU_EXECUTION ( ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_ID_, ACT_ID_, ACT_INST_ID_, IS_ACTIVE_, IS_CONCURRENT_, IS_SCOPE_, IS_EVENT_SCOPE_, PARENT_ID_, SUPER_EXEC_, SUPER_CASE_EXEC_, CASE_INST_ID_, SUSPENSION_STATE_, CACHED_ENT_STATE_, SEQUENCE_COUNTER_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
     * Result 5:	insert into ACT_RU_TASK ( ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, PRIORITY_, CREATE_TIME_, OWNER_, ASSIGNEE_, DELEGATION_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, CASE_EXECUTION_ID_, CASE_INST_ID_, CASE_DEF_ID_, TASK_DEF_KEY_, DUE_DATE_, FOLLOW_UP_DATE_, SUSPENSION_STATE_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
     */
    @Test
    public void startProcessInstanceByKey() {
        String processDefinitionKey = "leave2";
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey);
        System.out.println(processInstance.getCaseInstanceId() + "," + processInstance.getBusinessKey() + "," + processInstance.getProcessDefinitionId() + "," + processInstance.getRootProcessInstanceId());

    }

    /**
     * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_, RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_, RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_, RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_, RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_, RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_
     * from ACT_RU_TASK RES
     * WHERE ( 1 = 1 and RES.ASSIGNEE_ = ? )
     * order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createTaskQuery() {
        String assignee = "zhangsan";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        for (Task task : list) {
            System.out.println(task);
        }
    }

    /**
     * 完成任务
     * Result 0:	insert into ACT_HI_TASKINST ( ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, CASE_DEF_KEY_, CASE_DEF_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, ACT_INST_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, OWNER_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TASK_DEF_KEY_, PRIORITY_, DUE_DATE_, FOLLOW_UP_DATE_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 1:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 2:	insert into ACT_HI_ACTINST ( ID_, PARENT_ACT_INST_ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, CALL_CASE_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, ACT_INST_STATE_, SEQUENCE_COUNTER_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 3:	insert into ACT_RU_TASK ( ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, PRIORITY_, CREATE_TIME_, OWNER_, ASSIGNEE_, DELEGATION_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, CASE_EXECUTION_ID_, CASE_INST_ID_, CASE_DEF_ID_, TASK_DEF_KEY_, DUE_DATE_, FOLLOW_UP_DATE_, SUSPENSION_STATE_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
     * Result 4:	delete from ACT_RU_TASK where ID_ = ? and REV_ = ?	Update counts: [1]
     * Result 5:	update ACT_RU_EXECUTION set REV_ = ?, PROC_DEF_ID_ = ?, BUSINESS_KEY_ = ?, ACT_ID_ = ?, ACT_INST_ID_ = ?, IS_ACTIVE_ = ?, IS_CONCURRENT_ = ?, IS_SCOPE_ = ?, IS_EVENT_SCOPE_ = ?, PARENT_ID_ = ?, SUPER_EXEC_ = ?, SUSPENSION_STATE_ = ?, CACHED_ENT_STATE_ = ?, SEQUENCE_COUNTER_ = ?, TENANT_ID_ = ? where ID_ = ? and REV_ = ?	Update counts: [1]
     * Result 6:	UPDATE ACT_HI_ACTINST SET EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, ACT_ID_ = ?, ACT_NAME_ = ?, ACT_TYPE_ = ?, PARENT_ACT_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? , ACT_INST_STATE_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 7:	update ACT_HI_TASKINST set EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, DESCRIPTION_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, DELETE_REASON_ = ?, TASK_DEF_KEY_ = ?, PRIORITY_ = ?, DUE_DATE_ = ?, FOLLOW_UP_DATE_ = ?, CASE_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? where ID_ = ?	Update counts: [1]
     */
    @Test
    public void complete() {
        taskService.complete("6402");
    }

    @Test
    public void createTaskQuery2() {
        String assignee = "wangwu";
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();
        for (Task task : list) {
            System.out.println(task);
        }
    }

    @Test
    public void createProcessInstanceByKey() {
        ProcessInstance execute = runtimeService.createProcessInstanceByKey("leave2")
                .businessKey("001")
                .startBeforeActivity("Activity_1egig6v")
                .execute();
    }

    @Test
    public void createProcessInstanceByKey2() {
        ProcessInstance execute = runtimeService.createProcessInstanceByKey("leave2")
                .businessKey("001")
                .startTransition("Flow_0rtgpvv")
                .execute();
    }

    @Test
    public void createProcessInstanceByKey3() {
        ProcessInstance execute = runtimeService.createProcessInstanceByKey("leave2")
                .businessKey("001")
                .startTransition("Flow_0rtgpvv")
                .execute(true, true);
    }

    /**
     * 判断流程是否结束
     * select distinct RES.* from ACT_RU_EXECUTION RES inner join ACT_RE_PROCDEF P on RES.PROC_DEF_ID_ = P.ID_
     * WHERE RES.PARENT_ID_ is null and RES.PROC_INST_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createProcessInstanceByKey4() {
        String processInstanceId = "6201";

        ProcessInstance execute = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (execute == null) {
            System.out.println("当前实例已结束");
        } else {
            System.out.println("当前实例正在运转");
        }
    }

    /**
     * 69.查看流程是否结束
     * select distinct RES.*
     * from ( SELECT SELF.*, DEF.NAME_, DEF.VERSION_
     * FROM ACT_HI_PROCINST SELF LEFT JOIN ACT_RE_PROCDEF DEF ON SELF.PROC_DEF_ID_ = DEF.ID_
     * WHERE SELF.PROC_INST_ID_ = ? and STATE_ = ?
     * ) RES order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createHistoricProcessInstanceQuery() {
        String processInstanceId = "5801";

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .completed()
                .processInstanceId(processInstanceId)
                .singleResult();
        if (historicProcessInstance != null) {
            System.out.println("当前实例已结束");
        } else {
            System.out.println("当前实例正在运转");
        }
    }

    /**
     * 69.查询历史活动节点信息
     * select RES.* FROM ACT_HI_ACTINST RES
     * WHERE RES.PROC_INST_ID_ = ?
     * order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createHistoricActivityInstanceQuery() {
        String processInstanceId = "5801";

        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();

        for (HistoricActivityInstance instance : list) {
            System.out.println(instance);
        }
    }

    /**
     * select distinct RES.* from ACT_HI_TASKINST RES WHERE RES.PROC_INST_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
     * 69.查询历史任务信息
     */
    @Test
    public void createHistoricTaskInstanceQuery() {
        String processInstanceId = "5801";

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();

        for (HistoricTaskInstance instance : list) {
            System.out.println(instance);
        }
    }


    /**
     * 69.设置启动人以及initiator
     */
    @Test
    public void setAuthenticatedUserId() {
        identityService.setAuthenticatedUserId("xiaoming5");
        String processDefinitionKey = "leave2";
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey);
        System.out.println(processInstance.getCaseInstanceId() + "," + processInstance.getBusinessKey() + "," + processInstance.getProcessDefinitionId() + "," + processInstance.getRootProcessInstanceId());

    }

    /**
     * 71.测试删除流程实例
     * Result 0:	delete from ACT_RU_TASK where ID_ = ? and REV_ = ?	Update counts: [1]
     * Result 1:	delete from ACT_RU_EXECUTION where ID_ = ? and REV_ = ?	Update counts: [1]
     * Result 2:	UPDATE ACT_HI_ACTINST SET EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, ACT_ID_ = ?, ACT_NAME_ = ?, ACT_TYPE_ = ?, PARENT_ACT_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? , ACT_INST_STATE_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 3:	update ACT_HI_PROCINST set PROC_DEF_ID_ = ?, PROC_DEF_KEY_ = ?, BUSINESS_KEY_ = ?, END_ACT_ID_ = ?, DELETE_REASON_ = ?, SUPER_PROCESS_INSTANCE_ID_ = ?, STATE_ = ? , END_TIME_ = ? , DURATION_ = ? where ID_ = ?	Update counts: [1]
     * Result 4:	update ACT_HI_TASKINST set EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, DESCRIPTION_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, DELETE_REASON_ = ?, TASK_DEF_KEY_ = ?, PRIORITY_ = ?, DUE_DATE_ = ?, FOLLOW_UP_DATE_ = ?, CASE_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? where ID_ = ?	Update counts: [1]
     */
    @Test
    public void deleteProcessInstance() {
        runtimeService.deleteProcessInstance("6001", "测试删除");
    }

    /**
     * Result 0:	delete from ACT_RU_TASK where ID_ = ? and REV_ = ?	Update counts: [1, 1]
     * Result 1:	delete from ACT_RU_EXECUTION where ID_ = ? and REV_ = ?	Update counts: [1, 1]
     * Result 2:	UPDATE ACT_HI_ACTINST SET EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, ACT_ID_ = ?, ACT_NAME_ = ?, ACT_TYPE_ = ?, PARENT_ACT_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? , ACT_INST_STATE_ = ? WHERE ID_ = ?	Update counts: [1, 1]
     * Result 3:	update ACT_HI_PROCINST set PROC_DEF_ID_ = ?, PROC_DEF_KEY_ = ?, BUSINESS_KEY_ = ?, END_ACT_ID_ = ?, DELETE_REASON_ = ?, SUPER_PROCESS_INSTANCE_ID_ = ?, STATE_ = ? , END_TIME_ = ? , DURATION_ = ? where ID_ = ?	Update counts: [1, 1]
     * Result 4:	update ACT_HI_TASKINST set EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, DESCRIPTION_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, DELETE_REASON_ = ?, TASK_DEF_KEY_ = ?, PRIORITY_ = ?, DUE_DATE_ = ?, FOLLOW_UP_DATE_ = ?, CASE_INST_ID_ = ? , END_TIME_ = ? , DURATION_ = ? where ID_ = ?	Update counts: [1, 1]
     */
    @Test
    public void deleteProcessInstances() {
        List<String> list = new ArrayList<String>();
        list.add("5701");
        list.add("5901");
        runtimeService.deleteProcessInstances(list, "测试删除", true, true);
    }

    /**
     * select * from ACT_RU_EXECUTION where PROC_INST_ID_ = ?
     */
    @Test
    public void getActiveActivityIds() {
        String executionId = "6101";
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(executionId);
        System.out.println(activeActivityIds);
    }

    /**
     * select * from ACT_RE_PROCDEF where DEPLOYMENT_ID_ = ? and KEY_ = ?
     * select * from ACT_RE_PROCDEF RES
     * where KEY_ = ? and TENANT_ID_ = ? and VERSION_ = ( select max(VERSION_)
     * from ACT_RE_PROCDEF where KEY_ = ? and TENANT_ID_ = ?)
     */
    @Test
    public void getActivityInstance() {
        String processInstanceId = "6101";
        ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
        System.out.println(activityInstance);
    }

    /**
     * 73.流程定义挂起
     * Result 0:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_DEF_ID_ = ? and HANDLER_TYPE_ = ?	Update counts: [0]
     * Result 1:	update ACT_RE_PROCDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 2:	update ACT_RU_JOBDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     */
    @Test
    public void suspendProcessDefinitionById() {
        repositoryService.suspendProcessDefinitionById("leave2:1:5503");
    }

    /**
     * 73.激活流程定义
     * Result 0:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_DEF_ID_ = ? and HANDLER_TYPE_ = ?	Update counts: [0]
     * Result 1:	update ACT_RE_PROCDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 2:	update ACT_RU_JOBDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     * <p>
     * <p>
     * Result 0:	update ACT_RU_EXT_TASK SET SUSPENSION_STATE_ = ? WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     * Result 1:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_DEF_ID_ = ?	Update counts: [0]
     * Result 2:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_DEF_ID_ = ? and HANDLER_TYPE_ = ?	Update counts: [0]
     * Result 3:	update ACT_RU_TASK set REV_ = REV_ + 1, SUSPENSION_STATE_ = ?, CREATE_TIME_ = CREATE_TIME_ WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     * Result 4:	update ACT_RU_EXECUTION set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     * Result 5:	update ACT_RE_PROCDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 6:	update ACT_RU_JOBDEF set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_DEF_ID_ = ?	Update counts: [0]
     */
    @Test
    public void updateProcessDefinitionSuspensionState() {
        repositoryService.updateProcessDefinitionSuspensionState()
                .byProcessDefinitionId("leave2:1:5503")
                .includeProcessInstances(true)
                .activate();
    }

    /**
     * 73，挂起流程实例
     * 流程实例挂起，所有的执行实例都会挂起
     * 流程实例挂起之后，该实例的所有任务不能完成，强制完成任务会报错
     * <p>
     * Result 0:	update ACT_RU_EXT_TASK SET SUSPENSION_STATE_ = ? WHERE PROC_INST_ID_ = ?	Update counts: [0]
     * Result 1:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_INSTANCE_ID_ = ?	Update counts: [0]
     * Result 2:	update ACT_RU_TASK set REV_ = REV_ + 1, SUSPENSION_STATE_ = ?, CREATE_TIME_ = CREATE_TIME_ WHERE PROC_INST_ID_ = ?	Update counts: [1]
     * Result 3:	update ACT_RU_EXECUTION set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_INST_ID_ = ?	Update counts: [1]
     * Result 4:	update ACT_HI_PROCINST set PROC_DEF_ID_ = ?, PROC_DEF_KEY_ = ?, BUSINESS_KEY_ = ?, END_ACT_ID_ = ?, DELETE_REASON_ = ?, SUPER_PROCESS_INSTANCE_ID_ = ?, STATE_ = ? where ID_ = ?	Update counts: [1]
     */
    @Test
    public void suspendProcessInstanceById() {
        runtimeService.suspendProcessInstanceById("7301");
    }

    /**
     * 73.挂起多个流程实例
     * Result 0:	update ACT_RU_EXT_TASK SET SUSPENSION_STATE_ = ? WHERE PROC_INST_ID_ = ?	Update counts: [0, 0]
     * Result 1:	update ACT_RU_JOB set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROCESS_INSTANCE_ID_ = ?	Update counts: [0, 0]
     * Result 2:	update ACT_RU_TASK set REV_ = REV_ + 1, SUSPENSION_STATE_ = ?, CREATE_TIME_ = CREATE_TIME_ WHERE PROC_INST_ID_ = ?	Update counts: [1, 1]
     * Result 3:	update ACT_RU_EXECUTION set REV_ = REV_ + 1, SUSPENSION_STATE_ = ? WHERE PROC_INST_ID_ = ?	Update counts: [1, 1]
     * Result 4:	update ACT_HI_PROCINST set PROC_DEF_ID_ = ?, PROC_DEF_KEY_ = ?, BUSINESS_KEY_ = ?, END_ACT_ID_ = ?, DELETE_REASON_ = ?, SUPER_PROCESS_INSTANCE_ID_ = ?, STATE_ = ? where ID_ = ?	Update counts: [1, 1]
     */
    @Test
    public void updateProcessInstanceSuspensionState() {
        runtimeService.updateProcessInstanceSuspensionState()
//                .byProcessInstanceQuery(runtimeService.createProcessInstanceQuery())
                .byProcessInstanceIds("6801", "6701")
                .suspend();
    }
}

