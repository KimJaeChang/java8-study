package kr.co.kjc.java8_study.global.defalut_and_static;

public interface Bar extends DefaultAndStaticInterface {

  /**
   * @implSpec 해당 메소드는 DefaultInterface에선 Default 메소드이나 Bar에서 추상 메소드로 변경했기 때문에
   * Bar를 implements하는 구현체에서 @Override가 필요하다.
   */
  void printNameUpperCase();

}
