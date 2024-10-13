package kr.co.kjc.java8_study.service;

import java.util.function.Supplier;
import kr.co.kjc.java8_study.global.config.common.CommonConstant;
import kr.co.kjc.java8_study.global.custom_interface.java_function.SupplierExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

  /**
   * @apiNote
   * 매개변수 없이 Type 그대로 리턴
   * @return
   */
  public Integer getV1() {
    Supplier<Integer> supplierExample = () -> CommonConstant.NUM;
    return supplierExample.get();
  }

  public Integer getV2() {
    SupplierExample supplierExample = new SupplierExample();
    return supplierExample.get();
  }

}
