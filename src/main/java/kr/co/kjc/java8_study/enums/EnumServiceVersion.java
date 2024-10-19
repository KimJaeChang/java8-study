package kr.co.kjc.java8_study.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@AllArgsConstructor
public enum EnumServiceVersion {

  V1(1, "ver[1]"),
  V2(2, "ver[2]"),
  V3(3, "ver[3]"),
  V4(4, "ver[4]"),
  ;

  private Integer version;
  private String descriontion;
}
