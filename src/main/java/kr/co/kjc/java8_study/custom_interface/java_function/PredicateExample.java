package kr.co.kjc.java8_study.custom_interface.java_function;

import java.util.function.Predicate;
import kr.co.kjc.java8_study.global.constants.CommonConstants;

public class PredicateExample implements Predicate<String> {

  @Override
  public boolean test(String s) {
    return s.startsWith(CommonConstants.START_WITH_NAME);
  }

  @Override
  public Predicate<String> and(Predicate<? super String> other) {
    return Predicate.super.and(other);
  }

  @Override
  public Predicate<String> negate() {
    return Predicate.super.negate();
  }

  @Override
  public Predicate<String> or(Predicate<? super String> other) {
    return Predicate.super.or(other);
  }
}
