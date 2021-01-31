package com.company.stream;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));



        System.out.println("spring 으로 시작하는 수업");//스트림은 파이프 라인에 타입이 흘러가는 것. 여기선 Onlineclass
        springClasses.stream()
                .filter(oc->oc.getTitle().startsWith("spring"))
                .forEach(oc-> System.out.println(oc.getId()));


        System.out.println("close 되지 않은 수업");
        springClasses.stream()
                .filter(oc->!oc.isClosed())//이거 안에 Predicate.not(OnlineClss::isClosed)로 가능(임의의 객체에 임의이 인스턴스 메소드 참조하는 방법 참고)
                .forEach(oc-> System.out.println(oc.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(oc->oc.getTitle())//OnlineClass::gettitle, map은 타입을 변경해서 스트림을 만드는 경우가 많은 듯.
                .forEach(s-> System.out.println(s));//System.out::prinln

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> keesunEvents = new ArrayList<>();
        keesunEvents.add(springClasses);
        keesunEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");//flatmap은 각 덩어리를 요소들로 일렬로 세우는 느낌(강의 그림 참고)
        keesunEvents.stream().flatMap(list->list.stream())
                .forEach(oc-> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10,i->i+1)//무제한 스트림, 하지만 중간연산이기 때문에 여기까지 쓰면 무슨일 안일어남.
                .skip(10)
                .limit(10)
                .forEach(System.out::println);


        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test=javaClasses.stream().anyMatch(oc->oc.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목 만 모아서 List로 만들기");
        List<String> spring=springClasses.stream()
                .filter(oc->oc.getTitle().contains("spring"))//.map(oc->oc.getTitle())
                .map(oc->oc.getTitle())//map과 filter의 순서를 바꾸면 지나가는 타입을 바꿔서 해야할 것이다,.filter(t->t.contains("spring"))
                .collect(Collectors.toList());
        spring.forEach(System.out::println);



        ////////Optional
        OnlineClass spring_boot=new OnlineClass(1, "spring boot", true);
        //Duration studyDuration=spring_boot.getProgress().getStudyDuration();
        //System.out.println(studyDuration);//널포인트 익셉션 날라온다, 그래서 이걸 해결위해 아래와 같이

//        Progress progress=spring_boot.getProgress();
//        if(progress!=null){//이런식으로 해결할 수 있지만 번거롭고 까먹기 쉽다.
//            System.out.println(progress.getStudyDuration());//
//        }

        //따라서 리턴하는 쪽에 Optional 처리를 한다.
        Optional<Progress> progress=spring_boot.getProgress();
        progress.ifPresent(p-> System.out.println(p.getStudyDuration()));


        ////optional2
        Optional<OnlineClass> springg = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();//이렇듯 stream의 종료연산은 optional인 것이 타당하다

        boolean present = springg.isPresent();
        System.out.println(present);

        OnlineClass onlineClass1 = springg.get();//값이 들어있으면 상괍없는데 비어있다면 런타임익셉션(nosuchelement)생긴다.
        //따라서 if(optional.isPresent()){}로 감싸서 먼저 검사하고 꺼내는 행위를 해야한다. 하지만 이렇게 쓰기보다는 아래와 같이
        springg.ifPresent(oc->{
            System.out.println(oc.getTitle());
        });
        //없는 경우 어떤 일을 해라 하고 싶으면
        OnlineClass onlineClasss=springg.orElse(createNewClass());//orElse의 괄호 안에는 인스턴스가 들어오는 것,(functional interface가 아님)
        System.out.println(onlineClasss.getTitle());//없는 경우는 새로 생성한 애의 타이틀이 나올 것임.근데 사실 있는 거여도 createNewClass가 실행되긴 함. 실행되지 않게하고 싶으면 orElseGet사용

        OnlineClass onlineClasss2=springg.orElseGet(App::createNewClass);//이 경우는 괄호 안에 함수형 인터페이스의 구현체이기 때문에 있다면 실행 안함.

        //cf. orElseThrow 없으면 에러 던져라
        OnlineClass onlineClasss3=springg.orElseThrow(()->{
            return new IllegalStateException();
        });


        Optional<OnlineClass> onlineClassOptional=
                springg.filter(oc->!oc.isClosed());//filter활용해서 있으면 있는 옵셔널, 없으면 빈 옵셔녈 (filter의 결과도 optional이다)
        System.out.println(onlineClassOptional.isEmpty());

        Optional<Integer> integer = springg.map(OnlineClass::getId);
        System.out.println(integer.isPresent());



        //optional이 제공하는 flatmap과 stream이 제공하는 flatmap은 다르다.
        Optional<Progress> progresss = springg.flatMap(OnlineClass::getProgress);//flatmap을 쓰면 받아오는 것이 optional일 때 한번 까줌, 안그러면 까고까고해야함.
//        Optional<Optional<Progress>> progress1 = springg.map(OnlineClass::getProgress);
//        Optional<Progress> progress2 = progress1.orElse(Optional.empty());
    }
//git test 
    private static OnlineClass createNewClass() {
        return new OnlineClass(10,"New Class",false);
    }
}
