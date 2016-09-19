package com.yaojinwei.study.dubbo;

import com.le.jr.trade.usercenter.interfaces.UserInterfaces;
import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:root-context.xml"})
public class UserInterfaceTest {
    @Resource
    private UserInterfaces userInterfaces;

    @Test
    public void test(){
        userInterfaces.selectUserById(123123L);
    }
}
