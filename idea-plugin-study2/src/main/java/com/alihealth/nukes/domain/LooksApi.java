package com.alihealth.nukes.domain;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class LooksApi {

    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data;";
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";
    public static final String APPLICATION_JSON_VALUE = "application/json";

    private String project;
    private String key;
    private String url;
    private String method = "GET";
    private String name;
    private String desc;

    private LooksData response;
    private LooksRequestParameters parameters;
    private LooksRequestBody body;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LooksData getResponse() {
        return response;
    }

    public void setResponse(LooksData response) {
        this.response = response;
    }

    public LooksRequestParameters getParameters() {
        return parameters;
    }

    public void setParameters(LooksRequestParameters parameters) {
        this.parameters = parameters;
    }

    public LooksRequestBody getBody() {
        return body;
    }

    public void setBody(LooksRequestBody body) {
        this.body = body;
    }
}
