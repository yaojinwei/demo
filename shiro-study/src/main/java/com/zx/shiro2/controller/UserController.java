package com.zx.shiro2.controller;


import com.zx.shiro2.service.UserServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            userService.checkLogin(username,password);
            return "index";
        } catch (Exception e) {
            System.out.println("密码错误");
            return "login";
        }
    }
    @RequestMapping("/register")
    public String register(String username,String password){
        //注册时对密码进行加密
        Md5Hash md5Hash=new Md5Hash(password);
        //TODO 此处省略存储到数据库代码

        //加盐加密
        int salt=new Random().nextInt(90000)+10000;  //10000-99999
        Md5Hash md5Hash1=new Md5Hash(password,salt);
       //加盐加密+Hash次数
        Md5Hash md5Hash2=new Md5Hash(password,salt,1);

        return "login";
    }
    //没有sys:k:find权限不允许访问
    @RequiresPermissions("sys:k:find")
    @RequestMapping("/list")
    public String list(){
        return "list";
    }
}
