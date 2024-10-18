package kr.co.kjc.java8_study.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumJavaVersion {

  VER_5(5, "java5 버전"),
  VER_8(8, "java8 버전"),
  VER_17(17,"java17 버전"),
  ;

  private final int version;
  private final String description;

  public static boolean isBeforeVersion8(EnumJavaVersion enumJavaVersion) {
    return VER_8.getVersion() > enumJavaVersion.getVersion();
  }

}
