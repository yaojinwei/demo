package com.yaojinwei.study.dubbo;

import com.yaojinwei.study.dubbo.dto.ListenerDTO;
import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:root-context.xml"})
public class BaseTest {
    private Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Resource
    private SpeakInterface speakInterface;

    @Test
    public void test(){
        SpeakerDTO dto = new SpeakerDTO();
//        dto.setAge(22);
        dto.setContry("china");
        dto.setName("姚晋伟");

        logger.info(speakInterface.speak(dto).getData().toString());
        System.out.println("consumer end!");
    }
    @Test
    public void testEverySecond(){
        while(true){
            test();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
