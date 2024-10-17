package kr.co.kjc.java8_study.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumDataTransferType {
  
  CHARGER("charger", "충전기 정보"),
  MEMBER("member", "회원 정보"),
  ;
  
  private final String code;
  private final String description;
  
}
