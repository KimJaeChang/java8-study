package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class DefaultAndStaticMethodServiceTest {

  @Autowired
  DefaultAndStaticMethodService service;

  @Test
  void defaultMethodTest() {
    service.defaultMethodRun();
  }

  @Test
  void defaultMethodOverrideTest() {
    service.defaultMethodOverrideRun();
  }

  @Test
  void staticMethodRun() {
    service.staticMethodRun();
  }

}