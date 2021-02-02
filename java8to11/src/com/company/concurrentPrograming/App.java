package com.company.concurrentPrograming;

import org.w3c.dom.ls.LSOutput;

public class App {
    public static void main(String[] args) throws InterruptedException {
        //현재 스레드에서 다른 스레드를 만드는 방법1
//        MyThread myThread=new MyThread();
//        myThread.start();

        //현재 스레드에서 다른 스레드를 만드는 방법2
        Thread thread=new Thread(() -> {

            //while(true){
                System.out.println("Thread2 "+Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
            //}

        });
        thread.start();

        System.out.println("hello"+Thread.currentThread().getName());//현재 스레드, 하지만 순서 보장 안된다 thread1~~ 가 먼저 나올수도 있음.
//        Thread.sleep(3000L);
//        thread.interrupt();//이걸로 위의 스레드를 인터럽트 시킬 수 있다.
        thread.join();//이 스레드가 끝날때 까지 기다림.
        System.out.println(thread+"is finished");
        //만약 join부분에 인터럽트가 걸린다면? 복잡해짐.. 따라서 이걸 관리하는 것은 매우 어려움->excutor활용하자.


    }

    //현재 스레드에서 다른 스레드를 만드는 방법1
    static class MyThread extends Thread{

        @Override
        public void run() {
            System.out.println("thread1"+Thread.currentThread().getName());
        }
    }



}
