package com.yaojinwei.framework.sms;

/**
 * 短消息发送器
 * @author jinwei.yjw
 * @date 2018/3/26 16:11
 */
public interface SmsSender {

    SmsResult send(Sms sms);

    class SmsResult {
        String message;
        SmsResultStatus status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public SmsResultStatus getStatus() {
            return status;
        }

        public void setStatus(SmsResultStatus status) {
            this.status = status;
        }

        public static Builder builder(){
            return new Builder();
        }

        public static class Builder{
            private String message;
            private SmsResultStatus status;

            public Builder message(String message) {
                this.message = message;
                return this;
            }

            public Builder status(SmsResultStatus status) {
                this.status = status;
                return this;
            }

            public SmsResult build(){
                if(status == null){
                    status = SmsResultStatus.SUCCESS;
                }
                return create();
            }

            private SmsResult create(){
                SmsResult smsResult = new SmsResult();
                smsResult.setMessage(message);
                smsResult.setStatus(status);
                return smsResult;
            }
        }
    }

    enum SmsResultStatus {
        SUCCESS(1),FAILED(2);

        private int value;

        SmsResultStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }
}
