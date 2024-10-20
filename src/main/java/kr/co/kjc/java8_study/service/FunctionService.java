package kr.co.kjc.java8_study.service;

import java.util.function.Function;
import kr.co.kjc.java8_study.global.custom_interface.java_function.FunctionMultiply;
import kr.co.kjc.java8_study.global.custom_interface.java_function.FunctionPlus;
import kr.co.kjc.java8_study.global.constants.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FunctionService {

  /**
   * @apiNote
   * compose를 쓰면
   * 1.매개변수로 들어간 Function(multiply)를 실행시키고
   * 2. 그 다음 compose를 선언한 Function(plus10)을 실행
   * @param num
   * @return
   */
  public Integer composeV1(Integer num) {
    Function<Integer, Integer> plus = (i) -> i + CommonConstants.PLUS;
    Function<Integer, Integer> multiply = (i) -> i * CommonConstants.MULTIPLY;

    Function<Integer, Integer> multiplyAndPlus = plus.compose(multiply);

    System.out.println("multiplyAndPlus : " + multiplyAndPlus);

    Integer result = multiplyAndPlus.apply(num);
    System.out.println("composeV1 : " + result);
    return result;
  }

  public Integer composeV2(Integer num) {
    Function<Integer, Integer> plus = new FunctionPlus();
    Function<Integer, Integer> multiply = new FunctionMultiply();

    Function<Integer, Integer> multiplyAndPlus = plus.compose(multiply);

    System.out.println("multiplyAndPlus : " + multiplyAndPlus);

    Integer result = multiplyAndPlus.apply(num);
    System.out.println("composeV1 : " + result);
    return result;
  }

  public Integer composeFail(Integer num) {
    Function<Integer, Integer> plus = (i) -> num + CommonConstants.PLUS;
    Function<Integer, Integer> multiply = (i) -> num * CommonConstants.MULTIPLY;

    System.out.println("plus10 : " + plus.apply(3));
    System.out.println("multiply : " + multiply.apply(5));

    Function<Integer, Integer> multiplyAndPlus = multiply.compose(plus);

    System.out.println("multiplyAndPlus : " + multiplyAndPlus);

    Integer result = multiplyAndPlus.apply(num);
    System.out.println("composeV1 : " + result);
    return result;
  }

  /**
   * @apiNote
   * andThen을 쓰면
   * 1.andThen를 선언한 Function(plus)을 실행시키고
   * 2. 그 다음 매개변수로 들어간 Function(multiply)를 실행
   * @param num
   * @return
   */
  public Integer andThenV1(Integer num) {
    Function<Integer, Integer> plus = (i) -> i + CommonConstants.PLUS;
    Function<Integer, Integer> multiply = (i) -> i * CommonConstants.MULTIPLY;

    Function<Integer, Integer> multiplyAndPlus = plus.andThen(multiply);

    Integer result = multiplyAndPlus.apply(num);
    System.out.println("andThenV1 : " + result);
    return result;
  }

  public Integer andThenV2(Integer num) {
    Function<Integer, Integer> plus = new FunctionPlus();
    Function<Integer, Integer> multiply = new FunctionMultiply();

    Function<Integer, Integer> multiplyAndPlus = plus.andThen(multiply);

    Integer result = multiplyAndPlus.apply(num);
    System.out.println("andThenV2 : " + result);
    return result;
  }



}
