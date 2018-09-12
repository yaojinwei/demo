package com.yaojinwei.framework.excel;

import com.yaojinwei.framework.excel.toimport.config.ImportExcelConfig;

/**
 * @author jinwei.yjw
 * @date 2018/5/10 18:04
 */
public interface ExcelContext{
    void loadConfigsInClassPath(String... configLocations);
    ImportExcelConfig getImportConfigOfType(String importType);
}
