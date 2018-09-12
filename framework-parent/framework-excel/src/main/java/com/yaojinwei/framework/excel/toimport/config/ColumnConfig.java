package com.yaojinwei.framework.excel.toimport.config;

/**
 * 数据库列设置
 * @author jinwei.yjw
 * @date 2018/5/10 13:29
 */
public class ColumnConfig {
    /**
     * 列名
     */
    private String name;
    /**
     * 是否必填
     */
    private boolean required;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 数据长度
     */
    private Integer dataLength;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 取值表达式
     * # 表格值
     * @ 其他列的值
     * $ 函数
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
