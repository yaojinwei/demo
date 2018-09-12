package com.yaojinwei.framework.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.yaojinwei.framework.excel.common.util.ClassPathResourceUtils;
import com.yaojinwei.framework.excel.toexport.config.ExportConfig;
import com.yaojinwei.framework.excel.toimport.config.ImportConfig;
import com.yaojinwei.framework.excel.toimport.config.ImportExcelConfig;
import com.yaojinwei.framework.excel.toimport.config.XmlImportConfigParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * xml配置加载器
 * @author jinwei.yjw
 * @date 2018/5/10 15:07
 */
public class XmlResourceExcelContext implements ExcelContext{

    private Map<String, ImportExcelConfig> importConfigMap = new ConcurrentHashMap<>(16) ;
    public static final String IMPORT_ELEMENT = ImportConfig.IMPORT_ELEMENT;
    public static final String EXPORT_ELEMENT = ExportConfig.EXPORT_ELEMENT;

    @Override
    public void loadConfigsInClassPath(String... configLocations) {
        for(String cofigLocation:configLocations){

        }
    }


    private void loadConfigsInClassPath(String configLocation) {
        try {
            Document document = loadDocument(configLocation);
            registerConfigs(document);
        } catch (ParserConfigurationException e) {
            throw new ExcelConfigReadException("将资源文件[" + configLocation + "]转换成xml时出错！", e);
        } catch (IOException e) {
            throw new ExcelConfigReadException("读取资源文件[" + configLocation + "]出现异常！", e);
        } catch (SAXException e) {
            throw new ExcelConfigReadException("资源文件[" + configLocation + "]不可用！", e);
        }
    }

    private Document loadDocument(String configLocation) throws ParserConfigurationException, IOException,
        SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(ClassPathResourceUtils.getInputStream(this.getClass(), configLocation));
        return document;
    }

    private void registerConfigs(Document document ){
        // 获取根节点
        Element root = document.getDocumentElement();
        //
        if(IMPORT_ELEMENT.equals(root.getNodeName())){
            registerImportConfig(root);
        }
        else if(EXPORT_ELEMENT.equals(root.getNodeName())){

        }
    }

    private void registerImportConfig(Element element){

        new ImportConfig();
        XmlImportConfigParser parser = new XmlImportConfigParser();
        ImportConfig importConfig = parser
    }

    @Override
    public ImportExcelConfig getImportConfigOfType(String importType) {
        return null;
    }

}
