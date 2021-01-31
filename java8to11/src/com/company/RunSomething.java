package com.company;

@FunctionalInterface
public interface RunSomething {
    //추상 메서드가 하나만 있으면 함수형 인터페이스(다른 메소드가 있는 건 상관없음)
    int doIt(int number);//앞에 abstract 생략된것


    static void printName(){//자바8부터 인터페이스 내에 스태틱 메소드 사용할 수 있다.
        System.out.println("병현");
    }

    default void printAge(){//디폴트 메소드도 정의할 수 있다.
        System.out.println("40");
    }




}
