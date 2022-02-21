package com.alihealth.nukes.mock.dto;

import com.alihealth.nukes.domain.LooksApi;
import com.alihealth.nukes.domain.LooksRequestBody;
import com.alihealth.nukes.domain.LooksRequestParameters;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
//@Builder
//@AllArgsConstructor
public class MockApiDTO {
    private String project;
    private String key;
    private String url;
    private String method = "GET";
    private String name;
    private String desc;
    private MockResponseDTO response;
    private LooksRequestParameters getParam;
    private LooksRequestBody postParam;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public MockResponseDTO getResponse() {
        return response;
    }

    public void setResponse(MockResponseDTO response) {
        this.response = response;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LooksRequestParameters getGetParam() {
        return getParam;
    }

    public void setGetParam(LooksRequestParameters getParam) {
        this.getParam = getParam;
    }

    public LooksRequestBody getPostParam() {
        return postParam;
    }

    public void setPostParam(LooksRequestBody postParam) {
        this.postParam = postParam;
    }

    public static MockApiDTO createInstance(LooksApi api){
        MockApiDTO dto = new MockApiDTO();
        dto.setDesc(api.getDesc());
        dto.setKey(api.getKey());
        dto.setMethod(api.getMethod());
        dto.setProject(api.getProject());
        dto.setUrl(api.getUrl());
        dto.setName(api.getName());
        dto.setGetParam(api.getParameters());
        dto.setPostParam(api.getBody());
        if(api.getResponse() != null){
            dto.setResponse(new MockResponseDTO(api.getResponse().getChildren()));
        }
        return dto;
    }
}
