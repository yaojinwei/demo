package com.yaojinwei.study.java8.stream;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * https://www.jianshu.com/p/17d432f211f4
 *
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class ParallelStreamTest {
    public static void main0(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Calendar> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Calendar startDay = new GregorianCalendar();
            Calendar checkDay = new GregorianCalendar();
            checkDay.setTime(startDay.getTime());//不污染入参
            checkDay.add(Calendar.DATE, i);
            list.add(checkDay);
            checkDay = null;
            startDay = null;
        }

        list.stream().forEach(day -> System.out.println(sdf.format(day.getTime())));
        System.out.println("-----------------------");
        list.parallelStream().forEach(day -> System.out.println(sdf.format(day.getTime())));
        System.out.println("-----------------------");
    }

    public static void main1(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        List<LocalDate> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LocalDate date1 = date.plusDays(i);
            list.add(date1);
        }
        list.stream().forEach(day -> System.out.println(day.format(fmt)));
        System.out.println("-----------------------");
        list.parallelStream().forEach(day -> System.out.println(day.format(fmt)));
    }

    public static void main2(String[] args) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime date = LocalDateTime.now();
        List<LocalDateTime> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            LocalDateTime date1 = date.plusDays(i);
            list.add(date1);
        }
        list.stream().forEach(day -> System.out.println(day.format(fmt)));
        System.out.println("-----------------------");
        list.parallelStream().forEach(day -> System.out.println(day.format(fmt)));
    }


}
