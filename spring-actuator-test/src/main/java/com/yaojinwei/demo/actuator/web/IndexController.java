package com.yaojinwei.demo.actuator.web;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@RestController
@RequestMapping("api/health")
public class IndexController {
    @GetMapping
//    @Timed(value = "index.get")
    public String get() {
        return "success";
    }

    @RequestMapping("get2")
    public String get2() {
        return "success";
    }
}
