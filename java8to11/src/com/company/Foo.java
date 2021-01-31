package com.company;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Foo {

    public static void main(String[] args) {

        //익명 내부 클래스, 이걸 람다식으로 표현한것이 아래..
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("hello");
//                System.out.println(" lambda" );
//
//            }
//        };



//        RunSomething runSomething = (int number) -> {//함수 파라미터 혹은 내부에 있는 값면 변경,참조해야 한다(pure)
//            return number+10;
//        };//이 ()부터 끝에 이르기까지 자체를 변수에 넣으므로 이 자제를 리턴, 파라미터로 전달 등을 할수있다=first class object로 사용할 수 잇다.
//        //함수가 오브젝트이기 때문에 고차함수의 이야기도 가능하다.
//
//        System.out.println(runSomething.doIt(10));

        //Plus10 plus10=new Plus10();더 간단하게 클래스 plus10안만들고

        //Function<Integer,Integer> plus10=(i)->i+10;//인풋타입과 아웃풋 타입이 같으면 아래와 같이 쓸 수 있다
        UnaryOperator<Integer> plus10=(i)->i+10;
        Function<Integer,Integer> multiply2=(i)->i*2;

        Function<Integer,Integer> multiply2AndPlus10=plus10.compose(multiply2); //compose는 파라미터의 결과를 .앞에 값으로 활용.
        Function<Integer,Integer> Plus10AndMulitply2=plus10.andThen(multiply2);


        System.out.println(plus10.apply(1));
        System.out.println(multiply2AndPlus10.apply(2));
        System.out.println(Plus10AndMulitply2.apply(2));

        Consumer<Integer> printT=(i)-> System.out.println(i);//리턴이 없음. 받기만 함.
        printT.accept(10);

        Supplier<Integer> get10=()->10;//받지 않고 정의되 있는걸 가져오는 용
        System.out.println(get10.get());

        //뭘 하나 받아서 true or false를 리턴해주는 용도
        Predicate<String> startsWithAAA=(s)->s.startsWith("AAA");

        Foo foo=new Foo();
        foo.run();
    }

    private void run() {

        //final은 변경할 수 없, 따라서 만약 이 변수가 어디서도 변경되지 않는다면 final 키워드를 생략할 수 있다.(effective final)
        final int baseNumber=10;//이 지역변수는 캡쳐가 된다.


        //로컬클래스에서 로컬 변수를 참조할 수 있다.
        class localClass{
            void printBaseNumber(){
                int baseNumber=11;//이 변수가 외부의 baseNuber를 가린 것(쉐도잉)
                System.out.println(baseNumber);//11
            }
        }

        //익명클래에서 로컬변수를 참조할 수 있다.
        Consumer<Integer> integerConsumer=new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };


        //람다에서도 로컬병수(단 effective final)를 참조할 수 잇다. 위의 두클래스와 다르게 람다는 쉐도잉(같은 이름의 변수가 쓰이면 가려짐)이 되지 않는다. 즉 외부와 같은 scope이다, 즉 같은 이름의 변수를 정의할 수 없다.
        Consumer<Integer> pringInt=(i)-> System.out.println(i+baseNumber);
       // Consumer<Integer> pringInt=(baseNumber)-> System.out.println(baseNumber);//따라서 이런거 불가능하다.(같은 scope이기 때문에)

        pringInt.accept(10);

        ////////////////////////////1-4강의
        //UnaryOperator<String> hi=(s)->"hi "+s;//이런일을 원하는데 이게 그리팅에 있는거랑 독같은 일을 한다, 이때 아래와 같이 쓸 수 있다.
        UnaryOperator<String> hi=Greeting::hi;//스태틱 메소드 참

        Greeting greeting = new Greeting();
        UnaryOperator<String> hello=greeting::hello;//이렇게 인스턴스 메소드도 참조 할 수 있다. 메소드를 호출한건 아님
        System.out.println(hello.apply("kbh"));//이렇게 해야 뭔 일이 생기는 것임.

        Supplier<Greeting> newGreeting =Greeting::new;//생성자 참조.
        newGreeting.get();//이렇게 해야 인스턴스 만들어지는 것.

        Function<String,Greeting> bhGreeting=Greeting::new;
        Greeting bh=bhGreeting.apply("kbh");
        System.out.println(bh.getName());

        String[] names={"aaa","bbb","ccc"};
        //Arrays.sort(names, (o1, o2) -> 0);
        Arrays.sort(names, String::compareToIgnoreCase);//임의의 인스턴스를 거쳐가며 인스턴스 메소드를 참조한 것.
        System.out.println(Arrays.toString(names));

        FooInterface fooI=new DefaultFoo("kbh");
        fooI.printName();
        fooI.printNameUppercase();

        FooInterface.printAnything();

        List<String> name = new ArrayList<>();
        name.add("aa");
        name.add("bb");
        name.add("cc");
        name.add("dd");

        //name.forEach((s)-> System.out.println(s));
        name.forEach(System.out::println);

        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("----------------");
        while (spliterator1.tryAdvance(System.out::println));

        long k=name.stream().map(String::toUpperCase)
                .filter(s->s.startsWith("A"))
                .count();
        System.out.println(k);

        name.removeIf(s -> s.startsWith("a"));
        name.forEach(System.out::println);


        name.sort(String::compareToIgnoreCase);

        Comparator<String> compareToIgnoreCase=String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());//역 정렬

        ////////////////stream

        List<String> name2 = new ArrayList<>();
        name2.add("aa");
        name2.add("bb");
        name2.add("cc");
        name2.add("dd");

        //중간처리의 리턴은 stream, 종료 처리의 리턴은 stream이 아니다.
        name2.stream().map((s)->{
            System.out.println(s);
            return s.toUpperCase();
        });//출력되지 않는다, 즉 중간처리는 lazy하다.(종료연산이 와야 실행한다)


        List<String> collect=name2.stream().map((s)->{
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());

        collect.forEach(System.out::println);

        List<String> collect2=name2.parallelStream().map((s)->{
            System.out.println(s+" "+Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());//stream은 병렬처리가 가능하다. 찍어보면 수행 스레드가 다름.
        collect.forEach(System.out::println);


   }


}
