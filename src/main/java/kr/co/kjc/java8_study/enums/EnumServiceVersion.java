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
  V5(5, "ver[5]"),
  V6(6, "ver[6]"),
  V7(7, "ver[7]"),
  V8(8, "ver[8]"),
  V9(9, "ver[9]"),
  V10(10, "ver[10]"),
  V11(11, "ver[11]"),
  V12(12, "ver[12]"),
  ;

  private Integer version;
  private String descriontion;
}
