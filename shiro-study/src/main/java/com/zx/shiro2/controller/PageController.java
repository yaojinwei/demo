package com.zx.shiro2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/login.html")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String login1(){
        return "login";
    }
    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }
    @RequestMapping("/c_add.html")
    public String c_add(){
        return "c_add";
    }
    @RequestMapping("/c_delete.html")
    public String c_delete(){
        return "c_delete";
    }
}
