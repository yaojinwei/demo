package com.yaojinwei.study.dubbo.service;

import com.yaojinwei.study.dubbo.Messages;
import com.yaojinwei.study.dubbo.dto.ListenerDTO;
import com.yaojinwei.study.dubbo.dto.Message;
import com.yaojinwei.study.dubbo.dto.SpeakerDTO;
import com.yaojinwei.study.dubbo.interfaces.SpeakInterface;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Service("speackInterface")
public class SpeakInterfaceImpl implements SpeakInterface {

    private Logger logger = LoggerFactory.getLogger(SpeakInterfaceImpl.class);

    public Message speak(SpeakerDTO dto) {
        logger.info("The listener is:" + dto);
        Message message = Messages.success(1123456);
        return message;
    }
}
