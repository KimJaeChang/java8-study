package kr.co.kjc.java8_study.global.custom_interface.java_function;

import java.util.function.Function;
import kr.co.kjc.java8_study.global.constants.CommonConstants;

public class FunctionMultiply implements Function<Integer, Integer> {

  @Override
  public Integer apply(Integer integer) {
    return integer * CommonConstants.MULTIPLY;
  }

  @Override
  public <V> Function<V, Integer> compose(Function<? super V, ? extends Integer> before) {
    return Function.super.compose(before);
  }

  @Override
  public <V> Function<Integer, V> andThen(Function<? super Integer, ? extends V> after) {
    return Function.super.andThen(after);
  }

}
