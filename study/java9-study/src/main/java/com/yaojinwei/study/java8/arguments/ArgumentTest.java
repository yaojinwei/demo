package com.yaojinwei.study.java8.arguments;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jinwei.yjw
 * @date 2019-11-04 15:47
 */
public class ArgumentTest {

    public static void main(String[] args) {
        Book book = new Book();

        book.name = "十万个为什么";
        function1(book);
        System.out.println(book.name);

        function2(book);
        System.out.println(book.name);

        Map<String, Object> map = new HashMap<>();
        map.put("name", "第一次");
        function3(map);
        System.out.println(map.get("age"));

        Chair chair = new Chair(map);
        chair.map.put("gendor", "男");

        System.out.println(map.get("gendor"));

        Pair pair = new Pair(chair.getMap());
        pair.getMap().put("nation", "汉族");

        System.out.println(map.get("nation"));
    }

    private static void function1(Book book) {
        book.name = "百万个为什么";
    }

    private static void function2(Book book) {
        book = new Book();
        book.name = "千万个为什么";
    }

    private static void function3(Map<String, Object> map) {
        map.put("age", 12);

    }


    public static class Book{
        public String name;
    }

    public static class Chair{
        private Map<String, Object> map;

        public Chair(Map<String, Object> map) {
            this.map = map;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }
    }

    public static class Pair{
        private Map<String, Object> map;

        public Pair(Map<String, Object> map) {
            this.map = map;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }
    }
}
