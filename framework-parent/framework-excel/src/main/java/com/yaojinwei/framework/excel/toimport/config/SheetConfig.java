package com.yaojinwei.framework.excel.toimport.config;

import java.util.List;

/**
 * 对应数据表的sheet
 * 一个sheet的数据可以导入到多个数据库表，所以对应的有多个table
 * @author jinwei.yjw
 * @date 2018/5/10 13:28
 */
public class SheetConfig {
    /**
     * 根据索引定位sheet
     */
    private Integer sheetIndex;
    /**
     * 根据sheet名称定位sheet
     */
    private String sheetName;
    /**
     * 数据表结束标识
     */
    private String endFlag;
    /**
     * 表头行索引
     */
    private String headerRowIndex;
    /**
     * 数据行索引
     */
    private String dataRowIndex;
    /**
     * 要导入的数据表，支持多表导入
     */
    private List<TableConfig> tables;

    public Integer getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(Integer sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public String getHeaderRowIndex() {
        return headerRowIndex;
    }

    public void setHeaderRowIndex(String headerRowIndex) {
        this.headerRowIndex = headerRowIndex;
    }

    public String getDataRowIndex() {
        return dataRowIndex;
    }

    public void setDataRowIndex(String dataRowIndex) {
        this.dataRowIndex = dataRowIndex;
    }

    public List<TableConfig> getTables() {
        return tables;
    }

    public void setTables(List<TableConfig> tables) {
        this.tables = tables;
    }
}
