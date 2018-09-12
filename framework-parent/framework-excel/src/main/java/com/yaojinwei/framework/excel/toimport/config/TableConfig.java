package com.yaojinwei.framework.excel.toimport.config;

import java.util.List;

/**
 * 数据库表配置，考虑到多表导入的复杂性，以数据库的视角来配置
 * @author jinwei.yjw
 * @date 2018/5/10 13:29
 */
public class TableConfig {
    /**
     * 数据表名
     */
    private String name;
    /**
     * 是否删除重复数据
     */
    private boolean deleteRepeat;
    /**
     * 数据表对应多个列
     */
    private List<ColumnConfig> columns;
    /**
     * 容错方式
     * 单条数据出错的处理方式：1、忽略，2、撤销已导入数据  3、终止当前操作
     */
    private String errorHandleType ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleteRepeat() {
        return deleteRepeat;
    }

    public void setDeleteRepeat(boolean deleteRepeat) {
        this.deleteRepeat = deleteRepeat;
    }

    public List<com.yaojinwei.framework.excel.toimport.config.ColumnConfig> getColumns() {
        return columns;
    }

    public void setColumns(List<com.yaojinwei.framework.excel.toimport.config.ColumnConfig> columns) {
        this.columns = columns;
    }

    public String getErrorHandleType() {
        return errorHandleType;
    }

    public void setErrorHandleType(String errorHandleType) {
        this.errorHandleType = errorHandleType;
    }
}
