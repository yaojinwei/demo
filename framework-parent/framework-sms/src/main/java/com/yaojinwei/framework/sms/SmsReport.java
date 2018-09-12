package com.yaojinwei.framework.sms;

/**
 * @author jinwei.yjw
 * @date 2018/4/20 11:48
 */
public interface SmsReport {

    String getPhoneNum();

    boolean getSuccess();

    String getSendTime();

    String getReportTime();

    String getOutId();

    String getReportId();

    String getErrorCode();

    String getErrorMessage();


    public void setReportId(String reportId);

    public void setPhoneNum(String phoneNum);

    public void setOutId(String outId);

    public void setSuccess(boolean success);

    public void setSendTime(String sendTime);

    public void setReportTime(String reportTime);

    public void setErrorCode(String errorCode);

    public void setErrorMessage(String errorMessage);

}
