package com.yaojinwei.spring.proxy;

import com.alihealth.wiseframework.authority.access.DataAccessConfig;
import com.alihealth.wiseframework.authority.access.DataAccessConfigConvert;
import org.springframework.stereotype.Component;

@Component
public class SubSellerScopeConfigConverter implements DataAccessConfigConvert {

    @Override
    public String[] getSupportTypes() {
        return new String[0];
    }

    @Override
    public DataAccessConfig convert(String s, String s1, String s2) {
        return null;
    }
}