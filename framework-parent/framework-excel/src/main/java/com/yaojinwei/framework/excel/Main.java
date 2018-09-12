package com.yaojinwei.framework.excel;

import com.yaojinwei.framework.excel.toimport.config.ImportExcelConfig;

/**
 * @author jinwei.yjw
 * @date 2018/5/11 15:08
 */
public class Main {
    public static void main(String[] args) {
        ExcelContext excelContext = new XmlResourceExcelContext();
        ImportExcelConfig config = excelContext.getImportConfigOfType("1");

    }
}
