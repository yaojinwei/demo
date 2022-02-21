package com.alihealth.nukes.mock.dto;

import com.alihealth.nukes.domain.LooksData;

import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class MockResponseDTO {
    private List<LooksData> data;

    public MockResponseDTO(List<LooksData> data) {
        this.data = data;
    }

    public List<LooksData> getData() {
        return data;
    }

    public void setData(List<LooksData> data) {
        this.data = data;
    }
}
