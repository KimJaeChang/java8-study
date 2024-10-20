package kr.co.kjc.java8_study.global.defalut_and_static;

public interface DefaultAndStaticInterface {

  void printName();

  /**
   * @implSpec
   * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
   */
  default void printNameUpperCase() {
    System.out.println("printNameUpperCase : " + getName().toUpperCase());
  }

  static void printAnything() {
    System.out.println("printAnything 로그");
  }


  String getName();

}
