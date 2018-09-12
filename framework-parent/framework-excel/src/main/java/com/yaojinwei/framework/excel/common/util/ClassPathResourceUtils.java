package com.yaojinwei.framework.excel.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jinwei.yjw
 * @date 2018/5/11 16:07
 */
public class ClassPathResourceUtils {

    public static InputStream getInputStream(Class clazz, String path) throws IOException {
        InputStream is;
        if (clazz != null) {
            is = clazz.getResourceAsStream(path);
        }
        else if (clazz.getClassLoader() != null) {
            is = clazz.getClassLoader().getResourceAsStream(path);
        }
        else {
            is = ClassLoader.getSystemResourceAsStream(path);
        }
        if (is == null) {
            throw new FileNotFoundException("class path resource [" + path + "] cannot be opened because it does not exist");
        }
        return is;
    }

}
