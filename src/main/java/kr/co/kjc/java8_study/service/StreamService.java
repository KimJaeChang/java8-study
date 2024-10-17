package kr.co.kjc.java8_study.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreamService {

  public void run() {
    getNames().stream().map(String::toUpperCase)
        .forEach(System.out::println);
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
