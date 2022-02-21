package com.yaojinwei.camunda.study.ch8;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.repository.*;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.UserTask;
import org.camunda.commons.utils.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class DeploymentTest {
    public IdentityService identityService;
    AuthorizationService authorizationService;
    ProcessEngineConfigurationImpl processEngineConfiguration;
    ManagementService managementService;
    RepositoryService repositoryService;

    @Before
    public void init() {
        processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("camunda.ch6.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        identityService = processEngine.getIdentityService();

        authorizationService = processEngine.getAuthorizationService();
        managementService = processEngine.getManagementService();
        repositoryService = processEngine.getRepositoryService();
        System.out.println(identityService);
    }

    /**
     * insert into ACT_RE_DEPLOYMENT(ID_, NAME_, DEPLOY_TIME_, SOURCE_, TENANT_ID_) values(?, ?, ?, ?, ?)
     * 3301(String), 测试流程(String), 2020-08-11 15:39:35.976(Timestamp), 本地测试(String), A系统(String)
     * insert into ACT_GE_BYTEARRAY( ID_, NAME_, BYTES_, DEPLOYMENT_ID_, GENERATED_, TENANT_ID_, TYPE_, CREATE_TIME_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, ?, 1)
     * 3302(String), diagram_1.bpmn(String), java.io.ByteArrayInputStream@3dddbe65(ByteArrayInputStream), 3301(String), false(Boolean), null, 1(Integer), 2020-08-11 15:39:35.99(Timestamp)
     * insert into ACT_RE_PROCDEF(ID_, CATEGORY_, NAME_, KEY_, VERSION_, DEPLOYMENT_ID_, RESOURCE_NAME_, DGRM_RESOURCE_NAME_, HAS_START_FORM_KEY_, SUSPENSION_STATE_, TENANT_ID_, VERSION_TAG_, HISTORY_TTL_, STARTABLE_, REV_) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )
     * process_123:1:3303(String), http://bpmn.io/schema/bpmn(String), null, process_123(String), 1(Integer), 3301(String), diagram_1.bpmn(String), null, false(Boolean), 1(Integer), A系统(String), null, null, true(Boolean)
     * DeploymentEntity[id=3301, name=测试流程, resources={diagram_1.bpmn=ResourceEntity[id=3302, name=diagram_1.bpmn, deploymentId=3301, generated=false, tenantId=null, type=1, createTime=Tue Aug 11 15:39:35 CST 2020]}, deploymentTime=Tue Aug 11 15:39:35 CST 2020, validatingSchema=true, isNew=true, source=本地测试, tenantId=A系统]
     */
    @Test
    public void addClasspathResource() {
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
                .addClasspathResource("diagram_1.bpmn")
                .deploy();

        System.out.println(deployment);

    }

    @Test
    public void addString() {
        //必须以bpmn或者bpmn20.xml为后缀
        String resourceName = "diagram_1.bpmn";
        String text = IoUtil.fileAsString("diagram_1.bpmn");

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addString(resourceName, text)
                .deploy();

        System.out.println(deployment);

    }


    @Test
    public void addInputStream() {
        //必须以bpmn或者bpmn20.xml为后缀
        String resourceName = "diagram_1.bpmn";
        InputStream resourceAsStream = DeploymentTest.class
                .getClassLoader().getResourceAsStream("diagram_1.bpmn");

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addInputStream(resourceName, resourceAsStream)
                .deploy();

        System.out.println(deployment);

    }

    @Test
    public void addZipInputStream() {
        //必须以bpmn或者bpmn20.xml为后缀
        InputStream resourceAsStream = DeploymentTest.class
                .getClassLoader().getResourceAsStream("bpmn.zip");
        ZipInputStream zipInputStream = new
                ZipInputStream(resourceAsStream);
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println(deployment);

    }

    @Test
    public void addDeploymentResourceById() {
        String deploymentId = "3301";
        String resourceId = "3302";

        //必须以bpmn或者bpmn20.xml为后缀
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addDeploymentResourceById(deploymentId, resourceId)
                .deploy();

        System.out.println(deployment);

    }

    @Test
    public void addDeploymentResourceByName() {
        String deploymentId = "4201";
        String resourceName = "diagram_1.bpmn";

        //必须以bpmn或者bpmn20.xml为后缀
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addDeploymentResourceByName(deploymentId, resourceName)
                .deploy();

        System.out.println(deployment);

    }

    @Test
    public void addDeploymentResourceByNames() {
        String deploymentId = "4101";

        List<String> names = new ArrayList<String>();
        names.add("diagram_1.bpmn");
        names.add("leave.bpmn");

        //必须以bpmn或者bpmn20.xml为后缀
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        Deployment deployment = deploymentBuilder
                .name("测试流程")
                .source("本地测试")
                .tenantId("A系统")
                //.nameFromDeployment("")
//                .addClasspathResource("diagram_1.bpmn")
                .addDeploymentResourcesByName(deploymentId, names)
                .deploy();

        System.out.println(deployment);

    }

    /**
     * select distinct RES.* from ACT_RE_PROCDEF RES WHERE (RES.SUSPENSION_STATE_ = ?) order by RES.ID_ asc LIMIT ? OFFSET ?
     * 1(Integer), 2147483647(Integer), 0(Integer)
     * select * from ACT_GE_BYTEARRAY where DEPLOYMENT_ID_ = ? and NAME_ = ?
     */
    @Test
    public void createProcessDefinitionQuery() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
                .active()
                .list();
        for (ProcessDefinition definition : list) {
            System.out.println(definition);
        }
    }

    /**
     * delete from ACT_RU_IDENTITYLINK where PROC_DEF_ID_ = ?
     * process_123:9:4303(String)
     * delete from ACT_RE_PROCDEF where ID_ = ?
     * process_123:9:4303(String)
     * DELETE FROM ACT_RU_JOBDEF where PROC_DEF_ID_ = ?
     * process_123:9:4303(String)
     */
    @Test
    public void deleteProcessDefinition() {
        String processDefinitionId = "process_123:9:4303";
        repositoryService.deleteProcessDefinition(processDefinitionId);
    }

    /**
     * delete from ACT_RU_IDENTITYLINK where PROC_DEF_ID_ = ?
     * 19:32:20.161 [main] DEBUG o.c.b.e.i.p.e.I.deleteIdentityLinkByProcDef - ==> Parameters: process_123:8:4203(String)
     * delete B from ACT_GE_BYTEARRAY B inner join ACT_HI_JOB_LOG J on B.ID_ = J.JOB_EXCEPTION_STACK_ID_ and J.JOB_EXCEPTION_STACK_ID_ is not null and J.PROCESS_DEF_ID_ = ?
     * 19:32:20.165 [main] DEBUG o.c.b.e.i.p.e.H.deleteExceptionByteArraysByIds_mysql - ==> Parameters: process_123:8:4203(String)
     * delete from ACT_RE_PROCDEF where ID_ = ?
     * 19:32:20.166 [main] DEBUG o.c.b.e.i.p.e.P.deleteProcessDefinitionsById - ==> Parameters: process_123:8:4203(String)
     * delete from ACT_HI_IDENTITYLINK where PROC_DEF_ID_ = ?
     * 19:32:20.166 [main] DEBUG o.c.b.e.i.p.e.H.deleteHistoricIdentityLinksByProcessDefinitionId - ==> Parameters: process_123:8:4203(String)
     * delete from ACT_HI_INCIDENT where PROC_DEF_ID_ = ? and PROC_INST_ID_ is null
     * 19:32:20.167 [main] DEBUG o.c.b.e.i.p.e.H.deleteHistoricIncidentsByProcessDefinitionId - ==> Parameters: process_123:8:4203(String)
     * delete from ACT_HI_JOB_LOG where PROCESS_DEF_ID_ = ?
     * 19:32:20.167 [main] DEBUG o.c.b.e.i.p.e.H.deleteHistoricJobLogByProcessDefinitionId - ==> Parameters: process_123:8:4203(String)
     * DELETE FROM ACT_RU_JOBDEF where PROC_DEF_ID_ = ?
     * 19:32:20.167 [main] DEBUG o.c.b.e.i.p.e.J.deleteJobDefinitionsByProcessDefinitionId - ==> Parameters: process_123:8:4203(String)
     * 级联删除
     * 如果该流程定一下没有正在运行的流程，则可以用普通删除，如果是有关联的信息，用级联删除
     * 一般用级联删除
     */
    @Test
    public void deleteProcessDefinitionCascade() {
        String processDefinitionId = "process_123:8:4203";
        repositoryService.deleteProcessDefinition(processDefinitionId, true);
    }

    @Test
    public void getProcessModel() throws IOException {
        String processDefinitionId = "process_123:7:4104";
        InputStream inputStream = repositoryService.getProcessModel(processDefinitionId);

        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/yaojinwei/Temp/1.bpmn"));
    }

    @Test
    public void getDecisionDiagram() throws IOException {
        String processDefinitionId = "leave:4:4607";
        InputStream inputStream = repositoryService.getProcessDiagram(processDefinitionId);

        FileUtils.copyInputStreamToFile(inputStream, new File("/Users/yaojinwei/Temp/1.png"));
    }

    /**
     * select distinct RES.* from ACT_RE_DEPLOYMENT RES order by RES.ID_ asc LIMIT ? OFFSET ?
     *
     * @throws IOException
     */
    @Test
    public void createDeploymentQuery() throws IOException {
        List<Deployment> list = repositoryService.createDeploymentQuery()
                .list();

        for (Deployment de : list) {
            System.out.println(de.getId());
        }
    }

    /**
     * select * from ACT_GE_BYTEARRAY where DEPLOYMENT_ID_ = ? and NAME_ = ?
     * select * from ACT_RE_PROCDEF where DEPLOYMENT_ID_ = ? and KEY_ = ?
     * select * from ACT_RE_PROCDEF RES where KEY_ = ? and TENANT_ID_ = ? and VERSION_ = ( select max(VERSION_) from ACT_RE_PROCDEF where KEY_ = ? and TENANT_ID_ = ?)
     */
    @Test
    public void getProcessDiagramLayout() {
        String processDefinitionId = "leave:4:4607";
        DiagramLayout processDiagramLayout = repositoryService.getProcessDiagramLayout(processDefinitionId);
        Map<String, DiagramElement> elements = processDiagramLayout.getElements();
        for (Map.Entry entry : elements.entrySet()) {
            System.out.println("============");
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
    }

    @Test
    public void bpmnModelInstance() {
        BpmnModelInstance bpmnModelInstance = Bpmn.createExecutableProcess("leave")
                .startEvent()
                .name("开始节点")
                .userTask()
                .name("申请人")
                .camundaCandidateUsers("张三")
                .exclusiveGateway()
                .name("排他网关")
                .condition("小于三天", "${day<5}")
                .userTask()
                .name("组长审批")
                .camundaCandidateUsers("李四")
                .endEvent()
                .findLastGateway()
                .builder()
                .condition("大于等于三天", "${day>=5}")
                .userTask()
                .name("组长审批")
                .camundaCandidateUsers("李四")
                .userTask()
                .name("项目经历审批")
                .camundaCandidateUsers("王五")
                .endEvent()
                .done();

        repositoryService.createDeployment()
                .addModelInstance("custom.bpmn", bpmnModelInstance)
                .deploy();
    }

       @Test
    public void getBpmnModelInstance() {
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance("leave:6:5005");
        Collection<UserTask> modelElementsByType = bpmnModelInstance.getModelElementsByType(UserTask.class);
        System.out.println("#####" + modelElementsByType);
    }

    @Test
    public void getBpmnModelInstance2() {
        BpmnModelInstance bpmnModelInstance = repositoryService.getBpmnModelInstance("leave:6:5005");
        Collection<UserTask> modelElementsByType = bpmnModelInstance.getModelElementsByType(UserTask.class);
        System.out.println("#####" + modelElementsByType);

        repositoryService.createDeployment()
                .addModelInstance("custom.bpmn", bpmnModelInstance)
                .deploy();
    }

}
