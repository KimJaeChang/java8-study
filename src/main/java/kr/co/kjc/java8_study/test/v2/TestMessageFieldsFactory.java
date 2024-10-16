package kr.co.kjc.java8_study.test.v2;

import java.util.List;
import kr.co.kjc.java8_study.dtos.DataTransferV2Dto;

public interface TestMessageFieldsFactory {

  List<DataTransferV2Dto.MessageFields> createMessageFields();

}
