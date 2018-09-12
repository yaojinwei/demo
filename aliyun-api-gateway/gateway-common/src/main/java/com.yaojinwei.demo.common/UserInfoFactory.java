package com.yaojinwei.demo.common;

import java.util.Random;

/**
 * @author Yao.Jinwei
 * @date 2016/10/17 15:59
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class UserInfoFactory {
    public static UserInfo createUserInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(UUIDUtil.getUUID());
        userInfo.setName("用户" + System.currentTimeMillis());
        return userInfo;
    }

}
