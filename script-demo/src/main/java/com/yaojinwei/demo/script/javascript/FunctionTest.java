package com.yaojinwei.demo.script.javascript;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @author jinwei.yjw
 * @date 2019/1/20 21:05
 */
public class FunctionTest {

    public static void main(String[] args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath(); // 获取targe路径
            System.out.println(path);
            // FileReader的参数为所要执行的js文件的路径
            engine.eval(new FileReader(path+ "js/JavaScript1.js"));
            engine.eval(new FileReader(path+ "js/JavaScript2.js"));
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                JavaScriptInterface executeMethod = invocable.getInterface(JavaScriptInterface.class);
                System.out.println(executeMethod.execute("str1", "str2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
