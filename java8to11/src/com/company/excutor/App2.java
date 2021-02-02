package com.company.excutor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService executorService= Executors.newFixedThreadPool(3);
        Callable<String> a=()->{
            Thread.sleep(3000L);
            return "a";
        };
        Callable<String> b=()->{
            Thread.sleep(1000L);
            return "b";
        };
        Callable<String> c=()->{
            Thread.sleep(2000L);
            return "c";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(a, b, c));//invokeAll은 다른 thread까지 다 끝날때 까지 기다림.
        for(Future<String> f: futures){
            System.out.println(f.get());
        }

        String s = executorService.invokeAny(Arrays.asList(a, b, c));//이거 같은 경우는 한 쓰레드 결과값 받자마자 바로 리
        System.out.println(s);


        executorService.shutdown();


    }
}
