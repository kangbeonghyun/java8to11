package com.company.completablefuture;

import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService= Executors.newFixedThreadPool(4);
//        Future<String> future = executorService.submit(() -> "hello");
//        future.get();//future의 문제점, 뭘 받아서 해야하는데 받을때 까지 get 이후의 코드는 실행할 수 없음.

//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {//runAsync를 쓸 경우 리턴 값이 없을 경우임,
//            System.out.println("Hello " + Thread.currentThread().getName());
//        });
//        future.get();//이렇게 get을 해야 위의 작업이 실행 되는 것이다.
//
//        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {//리턴값이 있는 경
//            System.out.println("Hello " + Thread.currentThread().getName());
//            return "hello";
//        });
//        System.out.println(future2.get());//이렇게 get을 해야 위의 작업이 실행 되는 것이다.

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "hello";
        }).thenApply((s)->{
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });//thenApply는 우리가 결과값으로 받은 값을 다른 타입으로 변경하는 것, 이렇듯 get전의 콜백을 등록할 수 있다
        System.out.println(future3.get());

        CompletableFuture<Void> future4=CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "hello";
        }).thenAccept((s)->{
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });//thenAccept는 그냥 받아서 활용하겠다.
        //cf thenRun은 그냥 리턴 상관없이 그냥 이후에 뭘 하겠다.
        System.out.println(future4.get());



    }
}
