package kr.co.kjc.java8_study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.kjc.java8_study.test.v1.DataTransferV1Dto;
import kr.co.kjc.java8_study.test.v1.TestDataTransferV1Service;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class TestDataTransferV1ServiceTest {

  TestDataTransferV1Service testDataTransferV1Service = new TestDataTransferV1Service();

  @Test
  void get() throws JsonProcessingException, ParseException {
    DataTransferV1Dto chargerDto = testDataTransferV1Service.get(
        EnumDataTransferType.CHARGER);
    System.out.println("chargerDto : " + chargerDto);

    JSONObject chargerJson = testDataTransferV1Service.getJson(EnumDataTransferType.CHARGER);
    System.out.println("chargerJson : " + chargerJson);

    DataTransferV1Dto memberDto = testDataTransferV1Service.get(
        EnumDataTransferType.MEMBER);
    System.out.println("memberDto : " + memberDto);

    JSONObject memberJson = testDataTransferV1Service.getJson(EnumDataTransferType.MEMBER);
    System.out.println("memberJson : " + memberJson);
  }
}