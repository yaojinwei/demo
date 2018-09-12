package com.yaojinwei.framework.excel.toimport;

import com.yaojinwei.framework.excel.ImportResult;

/**
 * @author jinwei.yjw
 * @date 2018/5/11 15:13
 */
public interface ImportListener {
    /**
     * 导入完成之后触发事件
     * @param result
     */
    void afterImport(ImportResult result);

    /**
     * 导入之前触发事件
     */
    void beforeImport();
}
