package kr.co.kjc.java8_study.service;

import java.util.function.Consumer;
import kr.co.kjc.java8_study.custom_interface.java_function.ConsumerExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerService {

  /**
   * @apiNote
   * 리턴값이 없고 소비시킨다.
   * @param num
   * @return
   */
  public void acceptV1(Integer num) {
    Consumer<Integer> consumerExample = (i) -> System.out.println(i);
    consumerExample.accept(num);
  }

  public void acceptV2(Integer num) {
    ConsumerExample consumerExample = new ConsumerExample();
    consumerExample.accept(num);
  }

}
