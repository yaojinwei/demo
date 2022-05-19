package com.yaojinwei.demo.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("test")
    public String test(){
        //对应的jsp页面，在jsp下对的路径以及页面名称
        return "index";
    }
}