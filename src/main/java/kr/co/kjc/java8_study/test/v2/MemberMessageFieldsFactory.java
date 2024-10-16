package kr.co.kjc.java8_study.test.v2;

import java.util.List;
import kr.co.kjc.java8_study.dtos.DataTransferV2Dto.MessageFields;
import kr.co.kjc.java8_study.dtos.DataTransferV2Dto;

public class MemberMessageFieldsFactory implements TestMessageFieldsFactory {
  @Override
  public List<DataTransferV2Dto.MessageFields> createMessageFields() {
    return DataTransferV2Dto.MemberMessageFields.of();
  }
}
