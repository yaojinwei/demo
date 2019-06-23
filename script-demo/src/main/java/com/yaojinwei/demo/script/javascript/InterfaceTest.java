package com.yaojinwei.demo.script.javascript;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.yaojinwei.demo.script.javascript.CallbackTest.Function;

/**
 * @author jinwei.yjw
 * @date 2019/1/20 21:31
 */
public class InterfaceTest{
    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");

        Install install = new Install();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath(); // 获取targe路径
        Bindings bindings = engine.createBindings();
        bindings.put("install", install);
        engine.eval(new FileReader(path+ "js/InterfaceTest.js"), bindings);

        System.out.println(install.getJavaScriptInterface().execute("1", "2"));

    }

    public static class Install{
        public Install() {
        }

        private  JavaScriptInterface javaScriptInterface;

        public void onInstall(JavaScriptInterface javaScriptInterface){
            this.javaScriptInterface = javaScriptInterface;
        }

        public JavaScriptInterface getJavaScriptInterface() {
            return javaScriptInterface;
        }

        public void setJavaScriptInterface(JavaScriptInterface javaScriptInterface) {
            this.javaScriptInterface = javaScriptInterface;
        }
    }
}
