package kr.co.kjc.java8_study.custom_interface.java_function;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import kr.co.kjc.java8_study.global.constants.CommonConstants;

public class UnaryOperatorPlus implements UnaryOperator<Integer> {

  @Override
  public Integer apply(Integer integer) {
    return integer + CommonConstants.PLUS;
  }

  @Override
  public <V> Function<V, Integer> compose(Function<? super V, ? extends Integer> before) {
    return UnaryOperator.super.compose(before);
  }

  @Override
  public <V> Function<Integer, V> andThen(Function<? super Integer, ? extends V> after) {
    return UnaryOperator.super.andThen(after);
  }
}
