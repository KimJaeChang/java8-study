package kr.co.kjc.java8_study.test.v2;

import java.util.List;

public class MemberMessageFieldsFactory implements TestMessageFieldsFactory {
  @Override
  public List<DataTransferV2Dto.MessageFields> createMessageFields() {
    return DataTransferV2Dto.MemberMessageFields.of();
  }
}
