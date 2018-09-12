package com.yaojinwei.demo.gateway;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yaojinwei.demo.common.*;


/**
 * @author Yao.Jinwei
 * @date 2016/10/17 15:49
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
@Controller
@RequestMapping(value = "/api/test")
public class TestController {
    @ResponseBody
    @RequestMapping(value = "getUserInfo/{token}")
    public UserInfo getUserInfo(@PathVariable("token") String token) {
        UserInfo userInfo = UserInfoFactory.createUserInfo();
        return userInfo;
    }
}
