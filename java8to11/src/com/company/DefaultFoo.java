package com.company;

public class DefaultFoo implements FooInterface{

    //만약 다른 인터페이스에서 같은 이름의 디폴트 메소드가 있고 여기서 그걸 implement 하려고 하면 충돌이 발생하므로 컴파일 에러가 발생한다.
    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

}
