package com.yaojinwei.demo.swagger.markdown;

import java.util.List;

public class Address {
    private String provence;
    private Float jingdu;
    private List<Remark> remarkList;

    public String getProvence() {
        return provence;
    }

    public void setProvence(String provence) {
        this.provence = provence;
    }

    public Float getJingdu() {
        return jingdu;
    }

    public void setJingdu(Float jingdu) {
        this.jingdu = jingdu;
    }

    public List<Remark> getRemarkList() {
        return remarkList;
    }

    public void setRemarkList(List<Remark> remarkList) {
        this.remarkList = remarkList;
    }
}
