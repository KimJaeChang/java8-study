package kr.co.kjc.java8_study.global.custom_interface.java_function;

import java.util.function.Supplier;
import kr.co.kjc.java8_study.global.config.common.CommonConstant;

public class SupplierExample implements Supplier<Integer> {

  @Override
  public Integer get() {
    return CommonConstant.PLUS;
  }
}
