package com.yaojinwei.framework.sms;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import com.yaojinwei.framework.common.DateUtils;
import com.yaojinwei.framework.common.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinwei.yjw
 * @date 2018/4/20 11:19
 */
public class LogMockSmsSender implements SmsSender {

    private static Logger logger = LoggerFactory.getLogger(LogMockSmsSender.class);
    private AtomicLong mockReportId = new AtomicLong();

    public LogMockSmsSender() {
    }

    @Override
    public SmsResult send(Sms sms) {
        logger.info("=======================发送短信=====================");
        logger.info("==>> phone:{}", sms.getPhone());
        logger.info("==>> templateId:{}", sms.getTemplateId());
        logger.info("==>> variables:{}", JsonHelper.object2json(sms.getVariables()));
        logger.info("==>> outId:{}", sms.getOutId());
        SmsReport smsReport = SimpleSmsReport.builder()
            .reportId(String.valueOf(mockReportId.incrementAndGet()))
            .success(true)
            .sendTime(new Date())
            .outId(sms.getOutId())
            .phoneNum(sms.getPhone())
            .errorCode("DELIVERED")
            .errorMessage("用户接收成功")
            .build();
        // 2秒后发送短信回执
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                smsReport.setReportTime(DateUtils.formatDate(new Date()));
                smsReportHandler.handle(smsReport);
            } catch (InterruptedException e) {
                logger.error("短信回调线程异常中断！", e);
            }

        }).start();
        return SmsResult.builder().message("LogMockSmsSender发送成功").status(SmsResultStatus.SUCCESS).build();
    }
}
