package com.yaojinwei.demo.script.javascript;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

/**
 * 测试脚本回调
 * @author jinwei.yjw
 * @date 2019/1/19 22:40
 */
public class CallbackTest {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String script = "fff.print()";
        //CompiledScript script = ((Compilable)engine).compile("function.print()");
        ScriptContext context = new SimpleScriptContext();
        Bindings bindings = engine.createBindings();
        bindings.put("fff", new Function());
        engine.eval(script, bindings);
    }

    public static class Function{
        public void print(){
            System.out.println("function print!");
        }
    }
}
