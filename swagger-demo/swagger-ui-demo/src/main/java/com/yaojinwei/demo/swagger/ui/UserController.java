package com.yaojinwei.demo.swagger.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinwei.yjw
 * @date 2018/12/11 11:06
 */
@RestController
public class UserController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
}
