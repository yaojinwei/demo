package com.yaojinwei.test.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jinwei.yjw
 * @date 2018/7/13 14:59
 */
public class RegexTest {
    public static void main(String[] args) {
        String regex = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher("123123");
        System.out.println(match.find());
    }
}

