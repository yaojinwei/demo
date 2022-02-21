package com.yaojinwei.demo.swagger.markdown;

import java.util.Date;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class User {
    private String name;
    private List<Address> addressList;
    private int[] hh;
    private Date birth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
