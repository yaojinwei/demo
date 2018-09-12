package com.yaojinwei.framework.excel.toimport.config;

import java.util.HashMap;
import java.util.Map;

import com.yaojinwei.framework.excel.ConfigParser;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import static com.yaojinwei.framework.excel.toimport.config.ImportExcelConfig.IMPORT_TYPE_PROPERTY;

/**
 * @author jinwei.yjw
 * @date 2018/5/11 16:50
 */
public class XmlImportConfigParser {
    private final Map<String, ConfigParser> parsers = new HashMap<String, ConfigParser>();
    //public ImportConfig parse(Element element){
    //
    //}
    public ImportExcelConfig parse(Element element){
        ImportExcelConfig importExcelConfig = new ImportExcelConfig();
        importExcelConfig.setImportType(element.getAttribute(IMPORT_TYPE_PROPERTY));
        NodeList nodeList = element.getChildNodes();

    }

    public void init(){

    }
}
