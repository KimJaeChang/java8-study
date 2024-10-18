package kr.co.kjc.java8_study.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import kr.co.kjc.java8_study.dtos.OnlineClassDto;
import kr.co.kjc.java8_study.dtos.ProgressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionalService {

  public void run() {

    List<OnlineClassDto> inflearnClasses = new ArrayList<OnlineClassDto>();
    inflearnClasses.add(OnlineClassDto.of(1, "Spring Boot", true));
    inflearnClasses.add(OnlineClassDto.of(2, "Spring Data Jpa", true));
    inflearnClasses.add(OnlineClassDto.of(3, "Spring Mvc", false));
    inflearnClasses.add(OnlineClassDto.of(4, "Spring Core", false));
    inflearnClasses.add(OnlineClassDto.of(5, "Rest Api Development", false));

    ProgressDto progressDto = ProgressDto.of(Duration.ofHours(1), false);
    OnlineClassDto onlineClassDto = OnlineClassDto.ofV2(6, "Spring data Redis", true, progressDto);

  }

}
