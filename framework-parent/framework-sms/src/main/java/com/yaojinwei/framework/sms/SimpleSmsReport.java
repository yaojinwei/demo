package com.yaojinwei.framework.sms;

import java.util.Date;

import com.alihealth.wit120.illness.common.util.DateUtils;

/**
 * @author jinwei.yjw
 * @date 2018/4/20 12:07
 */
public class SimpleSmsReport implements SmsReport {
    /**
     * 报告编号
     */
    private String reportId;
    /**
     * 手机号码
     */
    private String phoneNum;
    /**
     * 业务编号
     */
    private String outId;
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 回执时间
     */
    private String reportTime;
    /**
     * 错误码
     *
     * @see <a href="https://help.aliyun.com/document_detail/55323.html"></a>}
     */
    private String errorCode;
    /**
     * 错误信息
     */
    private String errorMessage;

    @Override
    public String getReportId() {
        return reportId;
    }
    @Override
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    @Override
    public String getPhoneNum() {
        return phoneNum;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }
    @Override
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String getOutId() {
        return outId;
    }
    @Override
    public void setOutId(String outId) {
        this.outId = outId;
    }
    @Override
    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getSendTime() {
        return sendTime;
    }
    @Override
    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String getReportTime() {
        return reportTime;
    }
    @Override
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
    @Override
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String reportId;
        private String phoneNum;
        private String outId;
        private boolean success;
        private String sendTime;
        private String reportTime;
        private String errorCode;
        private String errorMessage;

        public Builder reportId(String reportId) {
            this.reportId = reportId;
            return this;
        }

        public Builder phoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
            return this;
        }

        public Builder outId(String outId) {
            this.outId = outId;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder sendTime(String sendTime) {
            this.sendTime = sendTime;
            return this;
        }

        public Builder sendTime(Date sendTime) {
            this.sendTime = DateUtils.formatDate(sendTime);
            return this;
        }

        public Builder reportTime(String reportTime) {
            this.reportTime = reportTime;
            return this;
        }

        public Builder reportTime(Date reportTime) {
            this.reportTime = DateUtils.formatDate(reportTime);
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public SimpleSmsReport build(){
            if(reportId == null){
                throw new IllegalArgumentException("reportId == null");
            }
            if(phoneNum == null){
                throw new IllegalArgumentException("phoneNum == null");
            }

            if(sendTime == null){
                throw new IllegalArgumentException("sendTime == null");
            }

            if(reportTime == null){
                this.reportTime = DateUtils.formatDate(new Date());
            }

            if(!success){
                if(errorCode == null ){
                    throw new IllegalArgumentException("errorCode == null");
                }
                if(errorMessage == null){
                    throw new IllegalArgumentException("errorMessage == null");
                }
            }

            return create();
        }

        private SimpleSmsReport create(){
            SimpleSmsReport smsReport = new SimpleSmsReport();
            smsReport.setPhoneNum (this.phoneNum);
            smsReport.setSuccess(this.success);
            smsReport.setReportId(this.reportId);
            smsReport.setOutId (this.outId);
            smsReport.setSendTime (this.sendTime);
            smsReport.setReportTime (this.reportTime);
            smsReport.setErrorCode(this.errorCode);
            smsReport.setErrorMessage(this.errorMessage);
            return smsReport;
        }
    }

}
