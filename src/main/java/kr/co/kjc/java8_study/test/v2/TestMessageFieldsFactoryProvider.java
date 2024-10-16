package kr.co.kjc.java8_study.test.v2;

import java.util.Map;
import java.util.Optional;
import kr.co.kjc.java8_study.enums.EnumDataTransferType;

public class TestMessageFieldsFactoryProvider {

  private static final Map<EnumDataTransferType, TestMessageFieldsFactory> FACTORY_MAP = Map.of(
      EnumDataTransferType.CHARGER, new ChargerMessageFieldsFactory(),
      EnumDataTransferType.MEMBER, new MemberMessageFieldsFactory()
  );

  public static TestMessageFieldsFactory get(EnumDataTransferType enumDataTransferType) {
    return Optional.ofNullable(FACTORY_MAP.get(enumDataTransferType))
        .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 EnumDataTransferType 입니다."));
  }

}
