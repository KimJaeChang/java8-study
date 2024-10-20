package kr.co.kjc.java8_study.global.custom_interface.java_function;

import java.util.function.Supplier;
import kr.co.kjc.java8_study.global.constants.CommonConstants;

public class SupplierExample implements Supplier<Integer> {

  @Override
  public Integer get() {
    return CommonConstants.PLUS;
  }
}
