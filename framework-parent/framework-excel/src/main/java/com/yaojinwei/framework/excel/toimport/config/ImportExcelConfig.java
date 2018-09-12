package com.yaojinwei.framework.excel.toimport.config;

import java.util.List;

/**
 * excel配置
 * @author jinwei.yjw
 * @date 2018/5/10 15:33
 */
public class ImportExcelConfig {
    public static final String IMPORT_ELEMENT = "import";
    public static final String IMPORT_TYPE_PROPERTY = "type";

    /**
     * 导入类型
     */
    private String importType;
    /**
     * 一个excel配置对应多个sheet配置
     */
    private List<SheetConfig> sheetConfigs;

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public List<SheetConfig> getSheetConfigs() {
        return sheetConfigs;
    }

    public void setSheetConfigs(List<SheetConfig> sheetConfigs) {
        this.sheetConfigs = sheetConfigs;
    }
}
