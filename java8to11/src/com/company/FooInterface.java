package com.company;

public interface FooInterface {

    void printName();
    //근데 이후에 공통적으로 적용되는 기능을 추가했음 좋겠다. 그래서 아래와 같이
    //void printNameV2();//이런식으로 추가하면 기존에 구현 클래스에 다 에러생긴다.(추가한 것에대한 구현체가 없기 때문)
    //이러한 문제를 해결하기 위한 것이 디폴트
    /**
     * @ImplSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUppercase(){
        System.out.println("FOO");
        System.out.println(getName().toUpperCase());
    }

    static void printAnything(){
        System.out.println("fooo");
    }

    String getName();
}
