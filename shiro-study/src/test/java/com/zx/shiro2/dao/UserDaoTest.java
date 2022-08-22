package com.zx.shiro2.dao;

import com.zx.shiro2.Shiro2Application;
import com.zx.shiro2.beans.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Shiro2Application.class)
public class UserDaoTest {
    @Resource
    UserDao userDao;
    @org.junit.Test
    public void getUserByUsername() {
        User user = userDao.getUserByUsername("zhangsan");
        System.out.println(user);
    }
}