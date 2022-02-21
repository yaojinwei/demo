package com.yaojinwei.camunda.study.ch6;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngineConfiguration;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Picture;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.persistence.entity.GroupEntity;
import org.camunda.bpm.engine.impl.persistence.entity.TenantEntity;
import org.camunda.bpm.engine.impl.persistence.entity.UserEntity;
import org.camunda.commons.utils.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class IdmTest {

    public IdentityService identityService;


    @Before
    public void init() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("camunda.ch6.cfg.xml");
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        identityService = processEngine.getIdentityService();
        System.out.println(identityService);
    }

    @Test
    public void getIdentityService() {

    }

    /**
     * insert into ACT_ID_USER (ID_, FIRST_, LAST_, EMAIL_, PWD_, SALT_, REV_)
     * values ( ?, ?, ?, ?, ?, ?, 1 )
     */
    @Test
    public void saveUser() {
        UserEntity entity = new UserEntity();
        entity.setId("xiaoming4");
        entity.setEmail("xiaoming@qq.com");
        entity.setFirstName("小明");
        entity.setLastName("小明");
        entity.setPassword("1");
        identityService.saveUser(entity);
    }

    /**
     * select distinct RES.* from ACT_ID_USER RES order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createUserQuery() {
        List<User> list = identityService.createUserQuery()
                .list();
        for (User user : list) {
            System.out.println("###########");
            System.out.println(user.getId());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getEmail());
        }
    }

    /**
     * select distinct RES.* from ACT_ID_USER RES order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void createUserQueryPage() {
        int firstResult = 1;
        int maxResults = 5;
        List<User> list = identityService.createUserQuery()
                .listPage(firstResult, maxResults);
    }

    /**
     * delete from ACT_ID_MEMBERSHIP where USER_ID_ = ?
     * xiaoming(String)
     * delete from ACT_ID_TENANT_MEMBER where USER_ID_ = ?
     * xiaoming(String)
     * delete from ACT_ID_USER where ID_ = ? and REV_ = ?
     * xiaoming(String), 1(Integer)
     */
    @Test
    public void deleteUser() {
        String userId = "xiaoming";
        identityService.deleteUser(userId);
    }

    /**
     * insert into ACT_ID_GROUP (ID_, NAME_, TYPE_, REV_) values ( ?, ?, ?, 1 )
     * dep(String), 项目经理(String), workflow(String)
     */
    @Test
    public void saveGroup() {
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setId("dep");
        groupEntity.setName("项目经理");
        groupEntity.setType("workflow");
        identityService.saveGroup(groupEntity);
    }

    /**
     * select distinct RES.* from ACT_ID_GROUP RES order by RES.ID_ asc LIMIT ? OFFSET ?
     */
    @Test
    public void listGroup() {
        List<Group> groupList = identityService.createGroupQuery().list();
        for (Group group : groupList) {
            System.out.println("###########");
            System.out.println(group.getId());
            System.out.println(group.getName());
            System.out.println(group.getType());
        }
    }

    /**
     * insert into ACT_ID_MEMBERSHIP (USER_ID_, GROUP_ID_) values ( ?, ? )
     * Parameters: xiaoming(String), dep(String)
     */
    @Test
    public void createMembership() {
        String userId = "xiaoming";
        String groupId = "dep";
        identityService.createMembership(userId, groupId);
    }

    /**
     * delete from ACT_ID_MEMBERSHIP where GROUP_ID_ = ?
     * dep(String)
     * delete from ACT_ID_TENANT_MEMBER where GROUP_ID_ = ?
     * dep(String)
     * delete from ACT_ID_GROUP where ID_ = ? and REV_ = ?
     * dep(String), 1(Integer)
     */
    @Test
    public void deleteGroup() {
        String groupId = "dep";
        identityService.deleteGroup(groupId);
    }

    /**
     * insert into ACT_ID_TENANT (ID_, NAME_, REV_) values ( ?, ?, 1 )
     * a(String), A系统(String)
     */
    @Test
    public void createTenant() {
        TenantEntity entity = new TenantEntity();
        entity.setId("a");
        entity.setName("A系统");
        identityService.saveTenant(entity);
    }

    /**
     * insert into ACT_ID_TENANT_MEMBER (ID_, TENANT_ID_, USER_ID_, GROUP_ID_) values ( ?, ?, ?, ? )
     * 201(String), a(String), xiaoming(String), null
     */
    @Test
    public void createUserTenantMembership() {
        String userId = "xiaoming";
        String tenantId = "a";

        identityService.createTenantUserMembership(tenantId, userId);
    }

    /**
     * insert into ACT_ID_TENANT_MEMBER (ID_, TENANT_ID_, USER_ID_, GROUP_ID_) values ( ?, ?, ?, ? )
     * 301(String), a(String), null, dep(String)
     */
    @Test
    public void createGroupTenantMembership() {
        String tenantId = "a";
        String groupId = "dep";

        identityService.createTenantGroupMembership(tenantId, groupId);
    }

    /**
     * select distinct RES.* from ACT_ID_USER RES inner join ACT_ID_TENANT_MEMBER TM on RES.ID_ = TM.USER_ID_ WHERE TM.TENANT_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
     * a(String), 2147483647(Integer), 0(Integer)
     */
    @Test
    public void createUserQueryMemberTenant() {

        List<User> userList = identityService.createUserQuery().memberOfTenant("a")
                .list();
        for (User user : userList) {
            System.out.println("###########");
            System.out.println(user.getId());
            System.out.println(user.getFirstName());
            System.out.println(user.getLastName());
            System.out.println(user.getEmail());
        }
    }

    /**
     * select distinct RES.* from ACT_ID_GROUP RES inner join ACT_ID_TENANT_MEMBER TM on RES.ID_ = TM.GROUP_ID_ WHERE TM.TENANT_ID_ = ? order by RES.ID_ asc LIMIT ? OFFSET ?
     * a(String), 2147483647(Integer), 0(Integer)
     */
    @Test
    public void createGroupQueryMemberTenant() {

        List<Group> groupList = identityService.createGroupQuery().memberOfTenant("a")
                .list();
        for (Group group : groupList) {
            System.out.println("###########");
            System.out.println(group.getId());
            System.out.println(group.getName());
            System.out.println(group.getType());
        }
    }

    /**
     * insert into ACT_ID_INFO (ID_, USER_ID_, TYPE_, KEY_, VALUE_, PASSWORD_, PARENT_ID_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, 1 )
     *  401(String), xiaohong(String), account(String), xiaohongAccounName(String), xiaohongUserAccounName(String), java.io.ByteArrayInputStream@6970140a(ByteArrayInputStream), null
     *  402(String), null, null, a(String), a(String), null, 401(String)
     *  403(String), null, null, b(String), b(String), null, 401(String)
     */
    @Test
    public void setUserAccount() {
        String userId = "xiaohong";
        String userPassword = "";
        String accountName = "xiaohongAccounName";
        String accountUserName = "xiaohongUserAccounName";
        String accountPassword = "xiaohongaccountPassword";
        Map<String, String> accountDetail = new HashMap<String, String>();
        accountDetail.put("a", "a");
        accountDetail.put("b", "b");
        identityService.setUserAccount(userId, userPassword, accountName, accountUserName, accountPassword, accountDetail);
    }

    /**
     * select KEY_ from ACT_ID_INFO where USER_ID_ = ? and TYPE_ = ? and PARENT_ID_ is null
     * xiaohong(String), account(String)
     */
    @Test
    public void getUserAccountNames() {
        String userId = "xiaohong";

        List<String> userAccountNames = identityService.getUserAccountNames(userId);
        for(String name:userAccountNames){
            System.out.println(name);
        }
    }

    /**
     * insert into ACT_ID_INFO (ID_, USER_ID_, TYPE_, KEY_, VALUE_, PASSWORD_, PARENT_ID_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, 1 )
     *  501(String), xiaohong(String), userinfo(String), c(String), c(String), null, null
     */
    @Test
    public void setUserInfo() {
        String userId = "xiaohong";
        identityService.setUserInfo(userId,"c", "c");
    }

    /**
     * insert into ACT_ID_INFO (ID_, USER_ID_, TYPE_, KEY_, VALUE_, PASSWORD_, PARENT_ID_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, 1 )
     *  601(String), xiaohong(String), null, picture(String), 602(String), null, null
     * insert into ACT_GE_BYTEARRAY(ID_, NAME_, BYTES_, DEPLOYMENT_ID_, TENANT_ID_, TYPE_, CREATE_TIME_, ROOT_PROC_INST_ID_, REMOVAL_TIME_, REV_) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, 1 )
     *  602(String), png(String), java.io.ByteArrayInputStream@1c32886a(ByteArrayInputStream), null, null, 1(Integer), 2020-08-11 10:33:53.336(Timestamp), null, null
     */
    @Test
    public void setUserPicture() {
        String userId = "xiaohong";
        byte[] bytes = IoUtil.fileAsByteArray(new File("/Users/yaojinwei/Downloads/PASS青年2.png"));
        Picture picture = new Picture(bytes, "png");
        identityService.setUserPicture(userId, picture);
    }

    @Test
    public void deleteUserInfo() {
        String userId = "xiaohong";
        identityService.deleteUserInfo(userId, "c");
    }

}
