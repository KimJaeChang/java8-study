package kr.co.kjc.java8_study.service;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShadowService {

  public void accept() {
    run();
  }

  private void run() {
    int baseNumber = 10;

    // 로컬 클래스
    // 값은 11로 출력
    // Scope가 run()에 적용된  baseNumber = 10의 값을 가려버림
    class LocalClass {
      void printBaseNumber() {
        int baseNumber = 11;
        System.out.println("LocalClass_baseNumber : " + baseNumber);
      }
    }

    // 익명 클래스
    // 값은 11로 출력
    // Scope가 run()에 적용된 baseNumber = 10의 값을 가려버림
    Consumer<Integer> integerConsumer = new Consumer<Integer>() {
      int baseNumber = 11;
      @Override
      public void accept(Integer integer) {
        System.out.println("integerConsumer_baseNumber : " + baseNumber);
      }
    };

    // 람다
    // 컴파일 에러
    // 람다 자체가 run()과 같은 Scope에 종속되기 때문에
    // effective final을(int baseNumber = 11) 선언하지 못함
    IntConsumer intConsumer = (i) -> {
//      int baseNumber = 11; // 컴파일에러
      System.out.println("intConsumer_baseNumber : " + baseNumber);
    };

    intConsumer.accept(10);

//    baseNumber++; // 컴파일 에러
                    // effective final은 수정하지 못한다. (int baseNumber = 10)
  }

}
