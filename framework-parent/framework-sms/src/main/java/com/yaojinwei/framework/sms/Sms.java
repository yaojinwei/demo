package com.yaojinwei.framework.sms;

import java.util.Map;

/**
 * @author jinwei.yjw
 * @date 2018/3/27 21:41
 */
public class Sms {
    /**
     * 业务id
     */
    private String outId;
    /**
     * 模板编号
     */
    private String templateId;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 变量表
     */
    private Map variables;

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = variables;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String outId;
        private String templateId;
        private String phone;
        private Map variables;

        public Builder outId(String outId) {
            this.outId = outId;
            return this;
        }

        public Builder templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder variables(Map variables) {
            this.variables = variables;
            return this;
        }

        public Sms build(){
            if(phone == null || phone.length() == 0){
                throw new IllegalArgumentException("phone == null || phone.length() == 0 ");
            }
            if(templateId == null || templateId.length() == 0){
                throw new IllegalArgumentException("templateId == null || templateId.length() == 0");
            }
            return create();
        }
        private Sms create(){
            Sms sms = new Sms();
            sms.setOutId(outId);
            sms.setPhone(phone);
            sms.setTemplateId(templateId);
            sms.setVariables(variables);
            return sms;
        }
    }
}
