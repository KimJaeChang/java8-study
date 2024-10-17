package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StreamServiceTest {

  @Test
  void runApiTest() {
    StreamService streamService = new StreamService();
    streamService.runApi();
  }

}