package com.company.date;


import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class dateApp {
    public static void main(String[] args) {

        //기존 자바 time관련한 것들
        //        Date date = new Date();//settime으로 시간을 변경할 수 있음=mutable, 이런건 multithread 환경에서 부적절하다.
//        GregorianCalendar gregorianCalendar = new GregorianCalendar();
//        SimpleDateFormat dateFormat = new SimpleDateFormat();

        //기계용 시간
        Instant instant=Instant.now();
        System.out.println(instant);//UTC==GMT(둘다 똑같은 거임)

        //기계용 시간(내 지역 기준)
        ZoneId zone=ZoneId.systemDefault();
        System.out.println(zone);
        ZonedDateTime zonedDateTime=instant.atZone(zone);
        System.out.println(zonedDateTime);

        //인간용 시간
        LocalDateTime now=LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(now.format(MMddyyyy));

        //기간
        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday=LocalDate.of(2020,Month.JANUARY,13);
        Period period = Period.between(today, thisYearBirthday);
        System.out.println(period.getDays());

        Instant now1=Instant.now();
        Instant plus = now1.plus(10, ChronoUnit.SECONDS);
        Duration between = Duration.between(now1, plus);
        System.out.println(between.getSeconds());

        LocalDate parse = LocalDate.parse("07/15/1982", MMddyyyy);
        System.out.println(parse);

        Date date=new Date();
        Instant instant2=date.toInstant();
        Date newDate=Date.from(instant2);//이런식으로 레거시와 호환할 수 있다.

        LocalDateTime now3=LocalDateTime.now();
        LocalDateTime pluss=now3.plus(10,ChronoUnit.DAYS);//이전과 다르게 새로운 객체가 생성되는 것,




    }
}
