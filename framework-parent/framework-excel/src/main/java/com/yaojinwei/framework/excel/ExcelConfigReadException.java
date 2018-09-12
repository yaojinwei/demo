package com.yaojinwei.framework.excel;

/**
 * @author jinwei.yjw
 * @date 2018/5/11 15:59
 */
public class ExcelConfigReadException extends RuntimeException {
    private String configPath;

    public ExcelConfigReadException( String configPath, String message) {
        super(message);
        this.configPath = configPath;
    }

    public ExcelConfigReadException(String configPath, String message, Throwable cause) {
        super(message, cause);
        this.configPath = configPath;
    }

    public ExcelConfigReadException(String configPath, Throwable cause) {
        super(cause);
        this.configPath = configPath;
    }


    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
}
