package com.company;

public interface Bar extends FooInterface {

    //만약 여기서는 pringNameUpperCase()를 제공하고 싶지 않다고 한다면
    void printNameUppercase();//이렇게 추상메서드로 선언하면 Bar를 구현하는 애들은 이거 다 직접 재정의 해야한다.
}