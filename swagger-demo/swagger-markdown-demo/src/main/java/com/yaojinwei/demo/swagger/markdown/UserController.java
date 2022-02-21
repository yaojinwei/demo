package com.yaojinwei.demo.swagger.markdown;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author jinwei.yjw
 * @date 2018/12/11 11:06
 */
@RestController
@RequestMapping("api/")
public class UserController {
//    @RequestMapping("/hello")
//    public String index() {
//        return "Hello World";
//    }

    /**
     * 获取数据
     * @return
     */
    @RequestMapping("get")
    public ResponseMessage<User> get(){
        return null;
    }

    @RequestMapping("list")
    public ResponseMessage<List<User>> list(){
        return null;
    }

    @RequestMapping("user")
    public ResponseMessage<User> user(){
        return null;
    }

    @RequestMapping("string")
    public ResponseMessage<String> string(){
        return null;
    }

    @GetMapping("integer")
    public ResponseMessage<String> integer(){
        return null;
    }

    @RequestMapping("booealn")
    public ResponseMessage<Boolean> booealn(){
        return null;
    }

    @RequestMapping("date")
    public ResponseMessage<Date> date(){
        return null;
    }

    @RequestMapping("list1")
    public ResponseMessage<List<Integer>> list1(){
        return null;
    }

    @RequestMapping("array")
    public ResponseMessage<int[]> array(){
        return null;
    }
}
