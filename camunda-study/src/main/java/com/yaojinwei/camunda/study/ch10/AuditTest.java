package com.yaojinwei.camunda.study.ch10;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricIdentityLinkLog;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.persistence.entity.GroupEntity;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.DeploymentBuilder;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.IdentityLink;
import org.camunda.bpm.engine.task.Task;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class AuditTest {
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
                .createProcessEngineConfigurationFromResource("camunda.ch10.cfg.xml");
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
                .name("audit1")
                .source("本地测试")
//                .tenantId("A系统")
                //.nameFromDeployment("")
                .addClasspathResource("diagram_3.bpmn")
                .deploy();

        System.out.println(deployment);

    }

    /**
     * Result 0:	insert into ACT_HI_VARINST ( ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_INST_ID_, TENANT_ID_, CASE_DEF_KEY_, CASE_DEF_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, TASK_ID_, NAME_, REV_, VAR_TYPE_, CREATE_TIME_, REMOVAL_TIME_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_, STATE_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1, 1, 1, 1]
     * Result 1:	insert into ACT_HI_DETAIL ( ID_, TYPE_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_INST_ID_, CASE_DEF_KEY_, CASE_DEF_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, TASK_ID_, NAME_, REV_, VAR_INST_ID_, VAR_TYPE_, TIME_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_, SEQUENCE_COUNTER_, TENANT_ID_, OPERATION_ID_, REMOVAL_TIME_ ) values ( ?, 'VariableUpdate', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1, 1, 1, 1]
     * Result 2:	insert into ACT_HI_TASKINST ( ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, CASE_DEF_KEY_, CASE_DEF_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, ACT_INST_ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, OWNER_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, DELETE_REASON_, TASK_DEF_KEY_, PRIORITY_, DUE_DATE_, FOLLOW_UP_DATE_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 3:	insert into ACT_HI_PROCINST ( ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_KEY_, PROC_DEF_ID_, START_TIME_, END_TIME_, REMOVAL_TIME_, DURATION_, START_USER_ID_, START_ACT_ID_, END_ACT_ID_, SUPER_PROCESS_INSTANCE_ID_, ROOT_PROC_INST_ID_, SUPER_CASE_INSTANCE_ID_, CASE_INST_ID_, DELETE_REASON_, TENANT_ID_, STATE_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 4:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1, 1]
     * Result 5:	insert into ACT_HI_ACTINST ( ID_, PARENT_ACT_INST_ID_, PROC_DEF_KEY_, PROC_DEF_ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, EXECUTION_ID_, ACT_ID_, TASK_ID_, CALL_PROC_INST_ID_, CALL_CASE_INST_ID_, ACT_NAME_, ACT_TYPE_, ASSIGNEE_, START_TIME_, END_TIME_, DURATION_, ACT_INST_STATE_, SEQUENCE_COUNTER_, TENANT_ID_, REMOVAL_TIME_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1, 1, 1]
     * Result 6:	insert into ACT_RU_EXECUTION ( ID_, ROOT_PROC_INST_ID_, PROC_INST_ID_, BUSINESS_KEY_, PROC_DEF_ID_, ACT_ID_, ACT_INST_ID_, IS_ACTIVE_, IS_CONCURRENT_, IS_SCOPE_, IS_EVENT_SCOPE_, PARENT_ID_, SUPER_EXEC_, SUPER_CASE_EXEC_, CASE_INST_ID_, SUSPENSION_STATE_, CACHED_ENT_STATE_, SEQUENCE_COUNTER_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1, 1]
     * Result 7:	insert into ACT_RU_TASK ( ID_, NAME_, PARENT_TASK_ID_, DESCRIPTION_, PRIORITY_, CREATE_TIME_, OWNER_, ASSIGNEE_, DELEGATION_, EXECUTION_ID_, PROC_INST_ID_, PROC_DEF_ID_, CASE_EXECUTION_ID_, CASE_INST_ID_, CASE_DEF_ID_, TASK_DEF_KEY_, DUE_DATE_, FOLLOW_UP_DATE_, SUSPENSION_STATE_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
     * Result 8:	insert into ACT_RU_VARIABLE ( ID_, TYPE_, NAME_, PROC_INST_ID_, EXECUTION_ID_, CASE_INST_ID_, CASE_EXECUTION_ID_, TASK_ID_, BYTEARRAY_ID_, DOUBLE_, LONG_, TEXT_, TEXT2_, VAR_SCOPE_, SEQUENCE_COUNTER_, IS_CONCURRENT_LOCAL_, TENANT_ID_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1, 1, 1, 1]
     * Result 9:	insert into ACT_RU_IDENTITYLINK (ID_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_DEF_ID_, TENANT_ID_, REV_ ) values (?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1, 1]
     */
    @Test
    public void startProcessInstanceByKey() {
        String processDefinitionKey = "audit1";
        String businessKey = "test1";

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("userIds", "zhangsan");
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
        System.out.println(processInstance.getCaseInstanceId() + "," + processInstance.getBusinessKey() + "," + processInstance.getProcessDefinitionId() + "," + processInstance.getRootProcessInstanceId());

    }

    @Test
    public void complete() {
        taskService.complete("9320");
    }

    @Test
    public void findProcessTask(){
        String processInstanceId="9101";

        List<Task> list = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
//                .taskCandidateUser()
                .list();
        for(Task task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getCreateTime());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
//            task.get
        }
    }

    /**
     * select distinct RES.* from ACT_HI_TASKINST RES
     * WHERE RES.PROC_INST_ID_ = ? and RES.END_TIME_ is not null order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void findHistTask(){
        String processInstanceId="9101";
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey("test1")
//                .processInstanceId(processInstanceId)
                .finished()
                .list();
        for(HistoricTaskInstance task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
//            task.get
        }
    }

    @Test
    public void findMyTask(){
        String assignee="zhangsan";

        List<Task> list = taskService.createTaskQuery()

//                .taskCandidateUser()
                .taskAssignee(assignee)
                .list();
        for(Task task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getCreateTime());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
//            task.get
        }
    }

    /**
     * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_, RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_, RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_, RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_, RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_, RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_
     * from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_
     * WHERE ( 1 = 1 and ( RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.USER_ID_ = ? ) ) )
     * order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void findGroupTask(){
        String assignee="zhangsan";

        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(assignee)
                .list();
        for(Task task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getCreateTime());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
            System.out.println("############");
        }
    }

    /**
     * select * from ACT_RU_TASK where ID_ = ?
     * select * from ACT_RU_IDENTITYLINK where TASK_ID_ = ?
     */
    @Test
    public void getIdentityLinksForTask(){
        String taskId="7814";
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(taskId);
        for(IdentityLink link:identityLinksForTask){
            System.out.println("#############");
            System.out.println(link.getUserId());
        }
    }

    /**
     * select distinct RES.* from ACT_HI_IDENTITYLINK RES WHERE RES.TASK_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createHistoricIdentityLinkLogQuery(){
        String taskId="7814";
        List<HistoricIdentityLinkLog> list = historyService.createHistoricIdentityLinkLogQuery()
                .taskId("7814")
                .list();
        for(HistoricIdentityLinkLog link:list){
            System.out.println("#############");
            System.out.println(link.getUserId());
            System.out.println(link.getGroupId());
            System.out.println(link.getAssignerId());
            System.out.println(link.getOperationType());
        }
    }

    /**
     * 任务签收
     * Result 0:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 1:	update ACT_RU_TASK SET REV_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, PRIORITY_ = ?, CREATE_TIME_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, DELEGATION_ = ?, EXECUTION_ID_ = ?, PROC_DEF_ID_ = ?, CASE_EXECUTION_ID_ = ?, CASE_INST_ID_ = ?, CASE_DEF_ID_ = ?, TASK_DEF_KEY_ = ?, DESCRIPTION_ = ?, DUE_DATE_ = ?, FOLLOW_UP_DATE_ = ?, SUSPENSION_STATE_ = ?, TENANT_ID_ = ? where ID_= ? and REV_ = ?	Update counts: [1]
     * Result 2:	UPDATE ACT_HI_ACTINST SET EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, ACT_ID_ = ?, ACT_NAME_ = ?, ACT_TYPE_ = ?, PARENT_ACT_INST_ID_ = ? , ASSIGNEE_ = ? , TASK_ID_ = ? WHERE ID_ = ?	Update counts: [1]
     * Result 3:	update ACT_HI_TASKINST set EXECUTION_ID_ = ?, PROC_DEF_KEY_ = ?, PROC_DEF_ID_ = ?, NAME_ = ?, PARENT_TASK_ID_ = ?, DESCRIPTION_ = ?, OWNER_ = ?, ASSIGNEE_ = ?, DELETE_REASON_ = ?, TASK_DEF_KEY_ = ?, PRIORITY_ = ?, DUE_DATE_ = ?, FOLLOW_UP_DATE_ = ?, CASE_INST_ID_ = ? where ID_ = ?	Update counts: [1]
     */
    @Test
    public void claim(){
        String taskId="8418";
        String userId="lisi";
        taskService.claim(taskId, userId);
    }

    /**
     * 归还任务
     */
    @Test
    public void claimNull(){
        String taskId="8418";
        String userId="lisi";
        taskService.claim(taskId, null);
    }

    /**
     * 添加候选人
     *
     * Result 0:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 1:	insert into ACT_RU_IDENTITYLINK (ID_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, PROC_DEF_ID_, TENANT_ID_, REV_ ) values (?, ?, ?, ?, ?, ?, ?, 1 )	Update counts: [1]
     */
    @Test
    public void addCandidateUser(){
        String taskId="8418";
        String userId="zhangsan2";
        taskService.addCandidateUser(taskId, userId);
    }

    /**
     * 删除候选人
     *
     * Result 0:	insert into ACT_HI_IDENTITYLINK ( ID_, TIMESTAMP_, TYPE_, USER_ID_, GROUP_ID_, TASK_ID_, ROOT_PROC_INST_ID_, PROC_DEF_ID_, OPERATION_TYPE_, ASSIGNER_ID_, PROC_DEF_KEY_, TENANT_ID_, REMOVAL_TIME_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )	Update counts: [1]
     * Result 1:	delete from ACT_RU_IDENTITYLINK where ID_ = ?	Update counts: [1]
     */
    @Test
    public void deleteCandidateUser(){
        String taskId="8418";
        String userId="zhangsan2";
        taskService.deleteCandidateUser(taskId, userId);
    }

    @Test
    public void saveUser() {
        GroupEntity groupEntity1 = new GroupEntity();
        groupEntity1.setId("pt");
        groupEntity1.setName("普通员工");
        identityService.saveGroup(groupEntity1);

        GroupEntity groupEntity2 = new GroupEntity();
        groupEntity2.setId("jl");
        groupEntity2.setName("项目经理");
        identityService.saveGroup(groupEntity2);


        UserEntity entity1 = new UserEntity();
        entity1.setId("zs");
        identityService.saveUser(entity1);

        UserEntity entity2 = new UserEntity();
        entity2.setId("ls");
        identityService.saveUser(entity2);

        UserEntity entity3 = new UserEntity();
        entity3.setId("ww");
        identityService.saveUser(entity3);

        identityService.createMembership("zs", "jl");
        identityService.createMembership("ls", "jl");
        identityService.createMembership("ww", "pt");
    }


    /**
     * 组定义三种方式
     * 1、直接定义  2、变量 3、监听器
     *
     * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_, RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_, RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_, RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_, RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_, RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_
     * from ACT_RU_TASK RES
     * inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_
     * WHERE ( 1 = 1 and ( RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate' and ( I.GROUP_ID_ IN ( ? ) ) ) )
     * order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void findGroupTask2(){
        String assignee="pt";

        List<Task> list = taskService.createTaskQuery()
                .taskCandidateGroup(assignee)
                .list();
        for(Task task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getCreateTime());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
            System.out.println("############");
        }
    }

    /**
     * select distinct RES.REV_, RES.ID_, RES.NAME_, RES.PARENT_TASK_ID_, RES.DESCRIPTION_, RES.PRIORITY_, RES.CREATE_TIME_, RES.OWNER_, RES.ASSIGNEE_, RES.DELEGATION_, RES.EXECUTION_ID_, RES.PROC_INST_ID_, RES.PROC_DEF_ID_, RES.CASE_EXECUTION_ID_, RES.CASE_INST_ID_, RES.CASE_DEF_ID_, RES.TASK_DEF_KEY_, RES.DUE_DATE_, RES.FOLLOW_UP_DATE_, RES.SUSPENSION_STATE_, RES.TENANT_ID_
     * from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_
     * WHERE ( 1 = 1 and ( RES.ASSIGNEE_ is null and I.TYPE_ = 'candidate'
     *  and ( I.USER_ID_ = ? or I.GROUP_ID_ IN ( ? ) ) ) )
     * order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void findGroupTask3(){
        String assignee="ww";

        List<Task> list = taskService.createTaskQuery()
                .taskCandidateUser(assignee)
                .list();
        for(Task task:list){
            System.out.println("############");
            System.out.println(task.getId());
            System.out.println(task.getCreateTime());
            System.out.println(task.getName());
            System.out.println(task.getPriority());
            System.out.println(task.getExecutionId());
            System.out.println(task.getDescription());
            System.out.println("############");
        }
    }
}
