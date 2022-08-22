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
public class PermissionDaoTest {
    @Resource
    private PermissionDao permissionDao;
    @Test
    public void getPermissionByUsername() {
        Set<String> permissions = permissionDao.getPermissionByUsername("zhangsan");
        permissions.forEach(System.out::println);
    }
}