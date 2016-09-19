package com.yaojinwei.study.proxy.agent;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 自定义javaagent
 * 命令行使用方式：java -javaagent:../../proxy-agent/target/proxy-agent-1.0-SNAPSHOT.jar -jar "advanced-1.0-SNAPSHOT.jar"
 */
public class CustomAgent implements ClassFileTransformer {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new CustomAgent());
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.startsWith("java") && !className.startsWith("sun")) {
            System.out.println(className + " --> EXPORTED start...");
            int lastIndexOf = className.lastIndexOf("/") + 1;
            String fileName = className.substring(lastIndexOf) + ".class";
            System.out.println("fileName1111:" + fileName);
            String paths = "D:\\project\\mygit\\proxy-study\\proxy-agent\\exports\\";
            System.out.println("path::" + paths);
            exportClazzToFile(paths, fileName, classfileBuffer);
            System.out.println(className + " --> EXPORTED Succeess!");
        }
        return classfileBuffer;
    }

    /**
     *
     * @param dirPath
     * @param fileName
     * @param data
     */
    private void exportClazzToFile(String dirPath, String fileName, byte[] data) {
        try {
            File file = new File(dirPath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            System.out.println("exception occured while doing some file operation");
            e.printStackTrace();
        }
    }
}