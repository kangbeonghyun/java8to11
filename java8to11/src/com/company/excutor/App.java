package com.company.excutor;

import java.util.concurrent.*;

public class   App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService executorService= Executors.newSingleThreadExecutor();//thread 1개
//        executorService.execute(new Runnable() {//excute대신 summit을 써도 된다.
//            @Override
//            public void run() {
//                System.out.println("Thread "+Thread.currentThread().getName());
//            }
//        });

        ExecutorService executorService=Executors.newFixedThreadPool(2);
        executorService.submit(getRunnable("a"));
        executorService.submit(getRunnable("b"));
        executorService.submit(getRunnable("c"));
        executorService.submit(getRunnable("d"));
        executorService.submit(getRunnable("e"));//찍어보면 2개의 스레드로 이 작업들 수행한다.
        executorService.shutdown();

        ScheduledExecutorService executorService1=Executors.newSingleThreadScheduledExecutor();
        executorService1.schedule(getRunnable("hello"), 3, TimeUnit.SECONDS);//3초뒤에 실행

        //Runnable은void이기 때문에 스레드의 작업 결과를 리턴받을 수 없다, 이때 callable을 쓴다. 이떄 받아올 무언가가 있어야 하는데 그것이 future이다.

        Callable<String> hello=()->{
            Thread.sleep(2000L);
            return "hello";
        };

        Future<String> submit=executorService1.submit(hello);
        System.out.println(submit.isDone()); //상태체크
        System.out.println("start");

        //submit.cancel(true);//이렇게 하면 인터럽트 걸고 취소됨,이거 하고 이후에 get하면 에러.
        submit.get();//이 이전까지는 쭉 실행되다가 이걸 만나는 순간 결과 값이 올 때까지 기다림.(블록킹 콜)

        System.out.println("end");



    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
