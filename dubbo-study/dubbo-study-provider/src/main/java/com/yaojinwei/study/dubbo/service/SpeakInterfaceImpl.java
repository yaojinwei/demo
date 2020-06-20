package com.yaojinwei.study.dubbo.service;

import com.yaojinwei.study.dubbo.Messages;
import com.yaojinwei.study.dubbo.dto.ListenerDTO;
import com.yaojinwei.study.dubbo.dto.Message;
import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/21
 */
public class SpeakInterfaceImpl implements SpeakInterface {
    private Logger logger = LoggerFactory.getLogger(SpeakInterfaceImpl.class);

    private SpeakerDTO createSpeakDTO(){
        SpeakerDTO speakerDTO = new SpeakerDTO();
//        speakerDTO.setAge(1);
        speakerDTO.setName("第一个版本");
        speakerDTO.setContry("中国");
        return speakerDTO;
    }

    private ListenerDTO createListenerDTO(){
        ListenerDTO speakerDTO = new ListenerDTO();
        speakerDTO.setAge(1);
        speakerDTO.setName("姚晋伟");
        speakerDTO.setContry("美国");
//        speakerDTO.setVersion("1.444");
        return speakerDTO;
    }

    public Message speak(SpeakerDTO speakerDTO) {
        logger.info("The speaker is:(1.1)" + speakerDTO);
        ListenerDTO listenerDTO = createListenerDTO();
//        logger.info("The listener is:" + listenerDTO);
        Message message = Messages.success(listenerDTO);
        return message;
    }
}
