package com.yaojinwei.study.dubbo.interfaces;

import com.yaojinwei.study.dubbo.dto.Message;
import com.yaojinwei.study.dubbo.dto.SpeakerDTO;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/8/20
 */
public interface SpeakInterface {
    public Message speak(SpeakerDTO dto);
}
