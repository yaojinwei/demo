package com.yaojinwei.framework.excel.toimport;

import java.io.File;

/**
 * excel导接口类
 * @author jinwei.yjw
 * @date 2018/5/10 15:05
 */
public interface ExcelImporter {
    /**
     *
     * @param importType
     * @param file
     * @return
     */
    void importToDb(String importType, File file);
}
