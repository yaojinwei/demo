package com.zx.shiro2.dao;

import com.zx.shiro2.Shiro2Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Shiro2Application.class)
public class RoleDaoTest {
    @Resource
    private RoleDao roleDao;
    @Test
    public void getRoleNamesByUsername(){
        Set<String> username = roleDao.getRoleNamesByUsername("zhangsan");
        username.forEach(System.out::println);
    }
}