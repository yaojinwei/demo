package com.yaojinwei.study.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * @author Yao.Jinwei
 * @date 2017/9/7 11:48
 */
public class LinkedListTest {
    @Before
    public void setUp() throws Exception {

    }
    @Test
    public void testOffer(){
        LinkedList books=new LinkedList();
        books.offer("1");
        books.offer("2");
        books.offer("3");
        System.out.println(books);
    }

    @Test
    public void testOfferFirst(){
        LinkedList books=new LinkedList();
        books.offer("1");
        books.offerFirst("2");
        books.offer("3");
        System.out.println(books);
    }
    @Test
    public void testPush(){
        System.out.println("push 和  offer first效果一样");
        LinkedList books=new LinkedList();
        books.push("1");
        books.push("2");
        books.push("3");
        System.out.println(books);
    }
    @Test
    public void testPop(){

        LinkedList books=new LinkedList();
        books.offer("1");
        books.offer("2");
        books.offer("3");
        while(books.size()>0){
            System.out.println(books.pop());
        }
    }
}