package kr.co.kjc.java8_study.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
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

  private List<String> getNames() {

    List<String> names = new ArrayList<>();
    names.add("keesun");
    names.add("whiteship");
    names.add("toby");
    names.add("foo");

    return names;
  }

}
