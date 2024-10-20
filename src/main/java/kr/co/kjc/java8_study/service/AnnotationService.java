package kr.co.kjc.java8_study.service;

import java.util.Arrays;
import java.util.List;
import kr.co.kjc.java8_study.annotation.ChickenByTypeParameter;
import kr.co.kjc.java8_study.annotation.ChickenByTypeUse;
import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnotationService {

  public void run(EnumServiceVersion enumServiceVersion) {
    if(enumServiceVersion == EnumServiceVersion.V1) {
      runV1();
    } else if(enumServiceVersion == EnumServiceVersion.V2) {
      runV2();
    }
  }

  public void runV1() {
    FeelslikeChickenV1.print("ChickenByTypeParameter 호출합니다.");
  }

  public void runV2() {
    FeelslikeChickenV2.print("ChickenByTypeUse 호출합니다.");
    FeelslikeChickenV2.customMethod(new String[2]);
  }

  // @Chicken 부분에 @Target(ElementType.TYPE_PARAMETER)로 선언해서 제네릭 T 부분에 쓸 수 있다.
  static class FeelslikeChickenV1<@ChickenByTypeParameter T> {

    public static <@ChickenByTypeParameter C> void print(C c) { // 특정한 타입을 받는 제네릭 생성, 파라미터 부분에는 생성 불가
      System.out.println(c);
    }

  }

  // @Chicken 부분에 @Target(ElementType.TYPE_USE)로 선언해서 제네릭 T 부분에 쓸 수 있다.
  static class FeelslikeChickenV2<@ChickenByTypeUse T> {

    public static <@ChickenByTypeUse C> void print(@ChickenByTypeUse C c) { // 특정한 타입을 받는 제네릭 생성, 파라미터 부분에는 생성 가능
      System.out.println(c);
    }

    public static void customMethod(@ChickenByTypeUse String[] args) throws @ChickenByTypeUse RuntimeException {
      List<@ChickenByTypeUse String> names = Arrays.asList("keesun");
      System.out.println("names : " + names);
    }

  }

}
