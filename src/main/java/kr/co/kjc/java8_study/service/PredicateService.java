package kr.co.kjc.java8_study.service;

import java.util.List;
import java.util.function.Predicate;
import kr.co.kjc.java8_study.global.custom_interface.java_function.PredicateExample;
import kr.co.kjc.java8_study.global.constants.CommonConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PredicateService {

  private static final List<String> LIST = List.of(CommonConstants.NAME);

  public boolean andV1(String name) {
    Predicate<String> pridicateExample = (s) -> s.startsWith(name);
    return LIST.stream()
        .anyMatch(pridicateExample);
  }

  public boolean andV2(String name) {
    Predicate<String> pridicateExample = new PredicateExample();
    return LIST.stream()
        .anyMatch(pridicateExample);
  }

}
