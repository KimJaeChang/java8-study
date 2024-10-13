package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FunctionServiceTest {

  @Autowired
  FunctionService functionService;

  @Test
  @DisplayName("functionServiveTest1")
  void plus10V1Test() {
    Integer result = functionService.plus10V1(1);
    Assertions.assertThat(result).isEqualTo(11);
  }

  @Test
  @DisplayName("composeFailTest")
  void composeFailTest() {
    Integer result = functionService.composeFail(3);
    Assertions.assertThat(result).isEqualTo(14);
  }

  @Test
  @DisplayName("composeV1Test")
  void composeV1Test() {
    Integer result = functionService.composeV1(2);
    Assertions.assertThat(result).isEqualTo(14);
  }

  @Test
  @DisplayName("composeV2Test")
  void composeV2Test() {
    Integer result = functionService.composeV2(2);
    Assertions.assertThat(result).isEqualTo(14);
  }

  @Test
  @DisplayName("andThenV1Test")
  void andThenV1Test() {
    Integer result = functionService.andThenV1(2);
    Assertions.assertThat(result).isEqualTo(24);
  }

  @Test
  @DisplayName("andThenV2Test")
  void andThenV2Test() {
    Integer result = functionService.andThenV2(2);
    Assertions.assertThat(result).isEqualTo(24);
  }

}