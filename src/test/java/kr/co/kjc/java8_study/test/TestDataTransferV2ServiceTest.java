package kr.co.kjc.java8_study.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.co.kjc.java8_study.test.v2.DataTransferV2Dto;
import kr.co.kjc.java8_study.test.v2.TestDataTransferV2Service;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

class TestDataTransferV2ServiceTest {

  TestDataTransferV2Service testDataTransferV2Service = new TestDataTransferV2Service();

  @Test
  void get() throws JsonProcessingException, ParseException {
    DataTransferV2Dto chargerDto = testDataTransferV2Service.get(
        EnumDataTransferType.CHARGER);
    System.out.println("chargerDto : " + chargerDto);

    JSONObject chargerJson = testDataTransferV2Service.getJson(EnumDataTransferType.CHARGER);
    System.out.println("chargerJson : " + chargerJson);

    DataTransferV2Dto memberDto = testDataTransferV2Service.get(
        EnumDataTransferType.MEMBER);
    System.out.println("memberDto : " + memberDto);

    JSONObject memberJson = testDataTransferV2Service.getJson(EnumDataTransferType.MEMBER);
    System.out.println("memberJson : " + memberJson);
  }
}