package kr.co.kjc.java8_study.dtos;

import java.time.Duration;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProgressDto {

  private Duration studyDuration;
  private Boolean finished;

  public static ProgressDto of(Duration studyDuration, Boolean finished) {
    return ProgressDto.builder()
        .studyDuration(studyDuration)
        .finished(finished)
        .build();
  }
}
