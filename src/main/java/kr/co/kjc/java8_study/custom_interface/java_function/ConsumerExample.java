package kr.co.kjc.java8_study.custom_interface.java_function;

import java.util.function.Consumer;

public class ConsumerExample implements Consumer<Integer> {

  @Override
  public void accept(Integer integer) {
    System.out.println(integer);
  }

  @Override
  public Consumer<Integer> andThen(Consumer<? super Integer> after) {
    return Consumer.super.andThen(after);
  }
}
