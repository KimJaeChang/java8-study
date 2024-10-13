package kr.co.kjc.java8_study.service;

import java.util.function.UnaryOperator;
import kr.co.kjc.java8_study.global.config.common.CommonConstant;
import kr.co.kjc.java8_study.global.custom_interface.java_function.UnaryOperatorMultiply;
import kr.co.kjc.java8_study.global.custom_interface.java_function.UnaryOperatorPlus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnaryOperatorService {

  /**
   * Function을 상속받아서
   * 매개변수 T, R이 같을 때 사용 가능
   * @param num
   */
  public Integer andThenV1(Integer num) {
    UnaryOperator<Integer> plus = (i) -> i + CommonConstant.PLUS;
    UnaryOperator<Integer> multiply = (i) -> i * CommonConstant.MULTIPLY;

    Integer result = plus.andThen(multiply).apply(num);

    System.out.println("andThenV1 : " + result);

    return result;
  }

  public Integer andThenV2(Integer num) {
    UnaryOperator<Integer> plus = new UnaryOperatorPlus();
    UnaryOperator<Integer> multiply = new UnaryOperatorMultiply();

    Integer result = plus.andThen(multiply).apply(num);

    System.out.println("andThenV2 : " + result);

    return result;
  }

}
