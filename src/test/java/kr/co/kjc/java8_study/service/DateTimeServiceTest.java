package kr.co.kjc.java8_study.service;

import static org.junit.jupiter.api.Assertions.*;

import kr.co.kjc.java8_study.enums.EnumJavaVersion;
import org.junit.jupiter.api.Test;

class DateTimeServiceTest {

  DateTimeService dateTimeService = new DateTimeService();

  @Test
  void beforeRun() {
    dateTimeService.run(EnumJavaVersion.VER_5);
  }

  @Test
  void afterRun() {
    dateTimeService.run(EnumJavaVersion.VER_17);
  }
}