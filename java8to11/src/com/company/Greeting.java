package com.company;

public class Greeting {

    private String name;

    public Greeting(){
        //입력값 업음, 결과값 Greeing()객체의 타입

    }

    public String getName() {
        return name;
    }

    public Greeting(String name){
        this.name=name;
    }

    public String hello(String name){
        return "hello"+name;
    }

    public static String hi(String name){
        return "hi "+name;
    }
}
