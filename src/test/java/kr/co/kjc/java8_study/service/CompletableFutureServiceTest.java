package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import org.junit.jupiter.api.Test;

class CompletableFutureServiceTest {

  CompletableFutureService service = new CompletableFutureService();

  @Test
  void run() throws InterruptedException {
    service.run(EnumServiceVersion.V1);
  }
}