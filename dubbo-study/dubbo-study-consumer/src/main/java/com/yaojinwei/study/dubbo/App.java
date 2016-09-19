package com.yaojinwei.study.dubbo;

import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    private Logger logger = LoggerFactory.getLogger(App.class);
    private SpeakInterface speakInterface;

    public SpeakInterface getSpeakInterface() {
        return speakInterface;
    }

    public void setSpeakInterface(SpeakInterface speakInterface) {
        this.speakInterface = speakInterface;
    }

    public void test(){
        SpeakerDTO dto = new SpeakerDTO();
//        dto.setAge(22);
        dto.setContry("china");
        dto.setName("姚晋伟");

        logger.info(speakInterface.speak(dto).getData().toString());
        System.out.println("consumer end!");
    }

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

    public static void main( String[] args )
    {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:root-context.xml");
        context.start();
        App app = new App();
        app.setSpeakInterface((SpeakInterface)context.getBean("speakInterface"));
        app.testEverySecond();
    }
}
