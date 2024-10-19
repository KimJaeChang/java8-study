package kr.co.kjc.java8_study.global.config.converter.spring;

import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumServiceVersionConverter implements Converter<String, EnumServiceVersion> {

  @Override
  public EnumServiceVersion convert(String source) {
    return EnumServiceVersion.valueOf(source.toUpperCase());
  }

}
