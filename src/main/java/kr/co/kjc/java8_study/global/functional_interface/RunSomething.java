package kr.co.kjc.java8_study.global.functional_interface;

@FunctionalInterface
public interface RunSomething {

  void doIt();

  static void printName(String name) {
    System.out.println("printName : " + name);
  }

  default void printAge(int age) {
    System.out.println("printAge : " + age);
  }
}
