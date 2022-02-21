package com.yaojinwei.study.java8.stream;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ParallelLockTest {

    public static void main(String[] args) {
        Map<String, List<String>> map = new HashMap<>();
        map.put("aa", Collections.singletonList("1111"));
        map.put("bb", Collections.singletonList("22"));
        map.put("cc", Collections.singletonList("333"));

        for (int i = 0; i < 10000; i++) {
            List<String> collect = map.entrySet()
                    .stream().map(Map.Entry::getValue)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            CopyOnWriteArraySet<String> collect1 = collect.parallelStream()
                    .map(x -> {
                        List<String> arr = new ArrayList<>();
                        arr.add("q");
                        arr.add("w");
                        arr.add("e");
                        arr.add(x);
                        return arr;
                    })
                    .flatMap(List::stream)
                    .collect(Collectors.toCollection(CopyOnWriteArraySet::new));

            System.out.println(collect1);
        }

    }

    public static
}
