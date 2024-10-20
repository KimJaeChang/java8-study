package kr.co.kjc.java8_study.global.defalut_and_static;

public class DefaultFoo implements DefaultAndStaticInterface {

  String name;

  public DefaultFoo(String name) {
    this.name = name;
  }

  @Override
  public void printName() {
    System.out.println(this.name);
  }

  @Override
  public void printNameUpperCase() {
    System.out.println(this.name.toLowerCase());
  }

  @Override
  public String getName() {
    return this.name;
  }
}
