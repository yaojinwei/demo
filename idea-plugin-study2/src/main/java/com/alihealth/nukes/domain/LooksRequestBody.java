package com.alihealth.nukes.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class LooksRequestBody {
    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data;";
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON_VALUE = "application/json";

    private String contentType;
    private List<LooksData> params;

    public LooksRequestBody() {
        contentType = APPLICATION_JSON_VALUE;
        params = new ArrayList<>();
    }

    public LooksRequestBody(String contentType) {
        this.contentType = contentType;
        params = new ArrayList<>();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public List<LooksData> getParams() {
        return params;
    }

    public void setParams(List<LooksData> params) {
        this.params = params;
    }

    public static LooksRequestBody json(){
        return new LooksRequestBody(APPLICATION_JSON_VALUE);
    }

    public static LooksRequestBody form(){
        return new LooksRequestBody(MULTIPART_FORM_DATA_VALUE);
    }

    public static LooksRequestBody formUrlEncoded(){
        return new LooksRequestBody(APPLICATION_FORM_URLENCODED_VALUE);
    }

    public LooksRequestBody add(LooksData data){
        params.add(data);
        return this;
    }

    public LooksRequestBody addAll(List<LooksData> dataList){
        params.addAll(dataList);
        return this;
    }
}
