package kr.co.kjc.java8_study.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectionService {

  public void iteratorRun() {
    List<String> names = getNames();
    names.forEach(System.out::println);
  }

  /**
   * spliterator1.trySplit() : spliterator1에서의 결과값을 반으로 쪼갠다.
   * 지금 List에는 "keesun", "whiteship", "toby", "foo" 순서대로 적재가 되어있으나
   * trySplit()을 쓰면 2개, 2개로 쪼개지지만 순서가 섞일 수 있다.
   */
  public void splitIteratorRun() {

    List<String> names = getNames();

//    LinkedList<String> names = new LinkedList<>();
//    names.add("keesun");
//    names.add("whiteship");
//    names.add("toby");
//    names.add("foo");

    Spliterator<String> spliterator1 = names.spliterator();
    Spliterator<String> spliterator2 = spliterator1.trySplit();

    while (spliterator1.tryAdvance(System.out::println)) {

    }
    System.out.println("=======================================");

    while (spliterator2.tryAdvance(System.out::println)) {

    }
  }

  public void comparatorRun() {

    List<String> names = getNames();
    System.out.println("1. 초기 리스트 : " + names);

    // List.of로 리스트를 만들면 Immutable이라 정렬이 안된다.
    Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
    names.sort(compareToIgnoreCase);
    System.out.println("2. 리스트 문자열 순서대로 정렬 : " + names);

    names.sort(compareToIgnoreCase.reversed());
    System.out.println("3. 리스트 문자열 역순 정렬 : " + names);

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
