package com.yaojinwei.study.java8.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class CompletableFutureTest {

    public static CompletableFuture<String> getMessage() {
        return CompletableFuture.supplyAsync(() -> " I am here ")
                .thenCombine(CompletableFuture.supplyAsync(() -> " with you "), (ret, msg) -> ret + msg)
                .thenCompose(p -> CompletableFuture.supplyAsync(() -> p + " and everyone "));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(getMessage().get());
    }

}
