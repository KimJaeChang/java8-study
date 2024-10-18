package kr.co.kjc.java8_study.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class OnlineClassDto {

  private Integer id;
  private String title;
  private Boolean closed;
  public ProgressDto progressDto;

  public OnlineClassDto(Integer id, String title, Boolean closed) {
    this.id = id;
    this.title = title;
    this.closed = closed;
  }

  public OnlineClassDto(Integer id, String title, Boolean closed, ProgressDto progressDto) {
    this.id = id;
    this.title = title;
    this.closed = closed;
  }

  public static OnlineClassDto of(Integer id, String title, Boolean closed) {
    return OnlineClassDto.builder()
        .id(id)
        .title(title)
        .closed(closed)
        .build();
  }

  public static OnlineClassDto ofV2(Integer id, String title, Boolean closed, ProgressDto progressDto) {
    return OnlineClassDto.builder()
        .id(id)
        .title(title)
        .closed(closed)
        .progressDto(progressDto)
        .build();
  }
}
