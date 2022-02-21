package com.yaojinwei.camunda.study.ch7;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.authorization.*;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.camunda.bpm.engine.authorization.Groups.CAMUNDA_ADMIN;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ResourcesTest {

    public IdentityService identityService;
    AuthorizationService authorizationService;
    ProcessEngineConfigurationImpl processEngineConfiguration;
    ManagementService managementService;

    @Before
    public void init() {
        processEngineConfiguration = (ProcessEngineConfigurationImpl)ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("camunda.ch6.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        identityService = processEngine.getIdentityService();

        authorizationService = processEngine.getAuthorizationService();
        managementService = processEngine.getManagementService();
        System.out.println(identityService);
    }

    /**
     * select distinct RES.* from ACT_RU_AUTHORIZATION RES WHERE RES.USER_ID_ in ( ? ) order by RES.ID_ asc LIMIT ? OFFSET ?
     * demo(String), 2147483647(Integer), 0(Integer)
     */
    @Test
    public void queryUserAuthorization(){
        List<Authorization> list = authorizationService
                .createAuthorizationQuery()
                .userIdIn("xiaoming1")
                .list();

        for(Authorization authorization:list){
            System.out.println("##############");
            System.out.println(authorization.getId());
            System.out.println(authorization.getAuthorizationType());
            System.out.println(authorization.getGroupId());
            System.out.println(authorization.getResourceId());
            System.out.println(authorization.getResourceType());
            System.out.println(authorization.getUserId());
            System.out.println("##############");
        }
    }


    public void createAuthorization( String userId , String groupId, Resource resrouce, String resourceId, Permission[] permissions){
        Authorization authorization = authorizationService.createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
        authorization.setUserId(userId);
        authorization.setGroupId(groupId);
        authorization.setResource(resrouce);
        authorization.setResourceId(resourceId);
        authorization.setPermissions(permissions);

        authorizationService.saveAuthorization(authorization);
    }

    public static class TestResouce implements Resource{

        private String name;
        private int type;

        public TestResouce(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String resourceName() {
            return name;
        }

        public int resourceType() {
            return type;
        }
    }
    /////////admin
    /**
     * insert into ACT_RU_AUTHORIZATION ( ID_, TYPE_, GROUP_ID_, USER_ID_, RESOURCE_TYPE_, RESOURCE_ID_, PERMS_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, 1 )
     * 701(String), 1(Integer), null, xiaoming1(String), 0(Integer), 0(String), 2147483647(Integer)
     * insert into ACT_RU_AUTHORIZATION ( ID_, TYPE_, GROUP_ID_, USER_ID_, RESOURCE_TYPE_, RESOURCE_ID_, PERMS_, REV_ ) values ( ?, ?, ?, ?, ?, ?, ?, 1 )
     * 702(String), 1(Integer), null, xiaoming2(String), 4(Integer), 4(String), 2147483647(Integer)
     */
    @Test
    public void addAuthorization(){
        Resource resource1 = new TestResouce("resource1", 100);
        Resource resource2 = new TestResouce("resource2", 200);
        createAuthorization("xiaoming1", null, Resources.APPLICATION, "*", new Permission[]{Permissions.ALL});
        createAuthorization("xiaoming2", null, Resources.AUTHORIZATION, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization2(){
        createAuthorization("xiaoming4", null, Resources.APPLICATION, "*", new Permission[]{Permissions.ALL});
    }


    @Test
    public void addAuthorization3(){
        createAuthorization("xiaoming4", null, Resources.USER, "*", new Permission[]{Permissions.READ, Permissions.CREATE, Permissions.UPDATE});
    }

    /**
     * 授权访问资源:名称是组
     */
    @Test
    public void addAuthorization4(){
        createAuthorization("xiaoming4", null, Resources.GROUP, "*", new Permission[]{Permissions.READ, Permissions.CREATE, Permissions.UPDATE});
    }

    /**
     * 租户资源
     */
    @Test
    public void addAuthorization5(){
        createAuthorization("xiaoming4", null, Resources.TENANT, "*", new Permission[]{Permissions.READ, Permissions.CREATE, Permissions.UPDATE});
    }

    @Test
    public void addAuthorization6(){
        createAuthorization("xiaoming4", null, Resources.GROUP_MEMBERSHIP, "*", new Permission[]{Permissions.CREATE, Permissions.DELETE});
    }

    @Test
    public void addAuthorization7(){
        createAuthorization("xiaoming4", null, Resources.TENANT_MEMBERSHIP, "*", new Permission[]{Permissions.CREATE, Permissions.DELETE});
    }

    @Test
    public void addAuthorization8(){
        createAuthorization("xiaoming4", null, Resources.AUTHORIZATION, "*", new Permission[]{Permissions.READ, Permissions.CREATE, Permissions.UPDATE});
    }
    /////////cockpit
    @Test
    public void addAuthorization9(){
        createAuthorization("xiaoming4", null, Resources.PROCESS_DEFINITION, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization10(){
        createAuthorization("xiaoming4", null, Resources.DECISION_DEFINITION, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization11(){
        createAuthorization("xiaoming4", null, Resources.TASK, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization12(){
        createAuthorization("xiaoming4", null, Resources.TASK, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization13(){
        createAuthorization("xiaoming4", null, Resources.DEPLOYMENT, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization14(){
        createAuthorization("xiaoming4", null, Resources.BATCH, "*", new Permission[]{Permissions.ALL});
    }

    @Test
    public void addAuthorization15(){
        createAuthorization("xiaoming4", null, Resources.PROCESS_INSTANCE, "*", new Permission[]{Permissions.ALL});
    }

    /////////tasklist
    @Test
    public void addAuthorization16(){
        createAuthorization("xiaoming4", null, Resources.FILTER, "*", new Permission[]{Permissions.ALL});
    }

    /**
     * SELECT CASE WHEN EXISTS (SELECT ID_ FROM ACT_RU_AUTHORIZATION A WHERE A.TYPE_ = 1 AND A.USER_ID_ = ? AND A.PERMS_ & ? = ? AND A.RESOURCE_TYPE_ = ? AND A.RESOURCE_ID_ = '*') THEN 1 ELSE ( SELECT CASE WHEN EXISTS (SELECT ID_ FROM ACT_RU_AUTHORIZATION A WHERE A.TYPE_ = 0 AND A.USER_ID_ = '*' AND A.PERMS_ & ? = ? AND A.RESOURCE_TYPE_ = ? AND A.RESOURCE_ID_ = '*') THEN 1 ELSE null END ) END
     * xiaoming5(String), 8(Integer), 8(Integer), 4(Integer), 8(Integer), 8(Integer), 4(Integer)
     */
    @Test
    public void addAuthorization17(){
//        identityService.createMembership("xiaoming5", CAMUNDA_ADMIN);

        processEngineConfiguration.getAdminUsers().add("xiaoming5");
        processEngineConfiguration.setAuthorizationEnabled(true);

        identityService.setAuthenticatedUserId("xiaoming5");

        createAuthorization("xiaoming5", null, Resources.USER, "*", new Permission[]{Permissions.READ});
        managementService.getProperties();

    }

    @Test
    public void addAuthorization18(){
        createAuthorization("xiaoming5", null, Resources.AUTHORIZATION, "*", new Permission[]{Permissions.ALL});
    }
}
