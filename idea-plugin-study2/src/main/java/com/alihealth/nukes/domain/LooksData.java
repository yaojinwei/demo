package com.alihealth.nukes.domain;

import com.intellij.openapi.util.text.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
@Data
@AllArgsConstructor
public class LooksData {
    private String key;
    private String type;
    private String desc;
    private List<LooksData> children;
    private Map<String, String> rule = null;

    public LooksData() {
    }

    public LooksData(Map<String, String> rule) {
        this.rule = rule;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LooksData> getChildren() {
        return children;
    }

    public void setChildren(List<LooksData> children) {
        this.children = children;
    }

    public Map<String, String> getRule() {
        return rule;
    }

    public void setRule(Map<String, String> rule) {
        this.rule = rule;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean isEmpty(LooksData response) {
        return StringUtil.isEmpty(response.getKey());
    }

    public static final LooksData empty() {
        LooksData response = new LooksData();
        return response;
    }

    public static final LooksData float0(String key) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType(FieldType.Number.getCode());
        return response;
    }

    public static final LooksData int0(String key) {
        LooksData response = new LooksData(new HashMap<>());
        response.setKey(key);
        response.setType(FieldType.Number.getCode());
        response.getRule().put("type", "int");
        return response;
    }

    public static LooksData boolean0(String key) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType(FieldType.Boolean.getCode());
        return response;
    }

    public static LooksData object0(String key, List<LooksData> children) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType(FieldType.Object.getCode());
        response.setChildren(children);
        return response;
    }

    public static LooksData string0(String key) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType(FieldType.String.getCode());
        return response;
    }

    public static LooksData date0(String key) {
        LooksData response = new LooksData(new HashMap<>());
        response.setKey(key);
        response.setType(FieldType.String.getCode());
        response.getRule().put("type", "datetime");
        return response;
    }

    public static LooksData array(String key, LooksData item) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType(FieldType.Array.getCode());
        response.children = new ArrayList<>();
        response.children.add(item);
        return response;
    }

    public static LooksData reference(String key, String path) {
        LooksData response = new LooksData();
        response.setKey(key);
        response.setType("@" + path);
        return response;
    }

    public enum FieldType {
        Boolean("Boolean"),
        String("String"),
        Number("Number"),
        Array("Array"),
        Object("Object");

        private String code;

        FieldType(String code) {
            this.code = code;
        }

        public java.lang.String getCode() {
            return code;
        }
    }
}
