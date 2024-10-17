package kr.co.kjc.java8_study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kr.co.kjc.java8_study.dtos.OnlineClassDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamService {

  public void run() {

    Stream<String> stringStream = getNames().stream().map(m -> {
      System.out.println(m); // 얘는 중계 오퍼레이션이라 Sysout이 출력되지 않는다.
      return m.toUpperCase();
    });

    getNames().stream().map(String::toUpperCase)
        .forEach(System.out::println); // 종료 오퍼레이션에 써야지 Sysout에 출력이 된다.
  }

  public void reactiveRun() {
    getNames().parallelStream().map(m -> {
      System.out.println(Thread.currentThread().getName());
      return m.toUpperCase();
    }).forEach(System.out::println);
  }

  public void runApi() {

    List<OnlineClassDto> inflearnClasses = new ArrayList<OnlineClassDto>();
    inflearnClasses.add(OnlineClassDto.of(1, "Spring Boot", true));
    inflearnClasses.add(OnlineClassDto.of(2, "Spring Data Jpa", true));
    inflearnClasses.add(OnlineClassDto.of(3, "Spring Mvc", false));
    inflearnClasses.add(OnlineClassDto.of(4, "Spring Core", false));
    inflearnClasses.add(OnlineClassDto.of(5, "Rest Api Development", false));

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("Spring 으로 시작하는 수업");
    inflearnClasses.stream()
        .filter(f -> f.getTitle().startsWith("Spring"))
        .forEach(ff -> {
          System.out.println(ff.getTitle());
        });

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("Closed 되지 않은 수업");
    inflearnClasses.stream()
        .filter(f -> !f.getClosed())
        .forEach(ff -> System.out.println(ff.getTitle()));

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("Id = 수업 이름을 합친 데이터 스트림 만들기");
    inflearnClasses.stream()
        .map(m -> {
          return m.getId() + " = " + m.getTitle();
        })
        .toList()
        .forEach(System.out::println);

    List<OnlineClassDto> javaClasses = new ArrayList<OnlineClassDto>();
    javaClasses.add(OnlineClassDto.of(6, "The Java, Test", true));
    javaClasses.add(OnlineClassDto.of(7, "The Java, Code manipulation", true));
    javaClasses.add(OnlineClassDto.of(8, "The Java, 8 to 11", false));

    List<List<OnlineClassDto>> keesunEvents = new ArrayList<>();
    keesunEvents.add(inflearnClasses);
    keesunEvents.add(javaClasses);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("onlineClasses 와 javaClasses 내부의 Dto 출력");
    keesunEvents.stream()
        .flatMap(fm -> fm.stream()) // flatMap은 List<List<OnlineClassDto>>내부에 있는 List<OnlineClassDto>를 Stream mapping 처리한다.
        .forEach(System.out::println);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("javaClasses Dto 출력");
    keesunEvents.stream()
        .flatMap(fm -> fm.stream()) // flatMap은 List<List<OnlineClassDto>>내부에 있는 List<OnlineClassDto>를 Stream mapping 처리한다, flatMap은 mapping보단 컬렉션 내부의 자원을 꺼내는게 목적인듯 하다.
        .filter(f -> f.getTitle().startsWith("The Java"))
        .forEach(System.out::println);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
    keesunEvents.stream()
        .flatMap(fm -> fm.stream()) // flatMap은 List<List<OnlineClassDto>>내부에 있는 List<OnlineClassDto>를 Stream mapping 처리한다.
        .map(fmm -> fmm.getId())       // List<OnlineClassDto> 내부에 있는 OnlineClassDto의 Id를 기준으로 Stream mapping 처리한다.
        .forEach(System.out::println);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("onlineClasses 의 Spring Title 아이디만 출력");
    keesunEvents.stream()
        .flatMap(fm -> fm.stream()) // flatMap은 List<List<OnlineClassDto>>내부에 있는 List<OnlineClassDto>를 Stream mapping 처리한다.
        .filter(f -> f.getTitle().startsWith("Spring"))
        .map(OnlineClassDto::getId)
        .forEach(System.out::println);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("10부터 1씩 증가하는 무제한 Stream 중에서 앞에 10개 빼고 최대 20개 까지만");
    Stream.iterate(10, i -> i + 1) // seed 시작 지점
        .skip(10) // 앞에 10개 제외 , 10 ~ 19는 제외
        .limit(20)  // 최대 20개 , 20 ~ 39까지 출력
        .forEach(System.out::println);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("javaClasses 중에 Test가 들어있는 수업이 있는지 확인");
    boolean test = javaClasses.stream()
        .anyMatch(am -> am.getTitle().contains("Test"));
    System.out.println("test : " + test);

    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");

    System.out.println("inflearnClasses 중에 제목에 Rest가 들어간 것만 모아서 List로 만들기");
    inflearnClasses.stream()
        .filter(f -> f.getTitle().startsWith("Rest"))
        .collect(Collectors.toList())
        .forEach(System.out::println);


    System.out.println("\t");
    System.out.println("==========================");
    System.out.println("\t");
  }

  private List<String> getNames() {

    List<String> names = new ArrayList<>();
    names.add("keesun");
    names.add("whiteship");
    names.add("toby");
    names.add("foo");

    return names;
  }

}
