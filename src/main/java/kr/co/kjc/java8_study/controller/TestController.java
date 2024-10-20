package kr.co.kjc.java8_study.controller;

import kr.co.kjc.java8_study.dtos.BaseResponseDTO;
import kr.co.kjc.java8_study.enums.EnumServiceVersion;
import kr.co.kjc.java8_study.service.CompletableFutureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

  private final CompletableFutureService completableFutureService;

  @RequestMapping(value = "/{enum_version}/thread", method = RequestMethod.GET)
  public BaseResponseDTO<?> thread(@PathVariable("enum_version") EnumServiceVersion enumServiceVersion)
      throws InterruptedException {
    completableFutureService.run(enumServiceVersion);
    return new BaseResponseDTO<>(enumServiceVersion);
  }

  @RequestMapping(value = "/{enum_version}/executor", method = RequestMethod.GET)
  public BaseResponseDTO<?> executor(@PathVariable("enum_version") EnumServiceVersion enumServiceVersion) {
    completableFutureService.executorRun(enumServiceVersion);
    return new BaseResponseDTO<>(enumServiceVersion);
  }

  @RequestMapping(value = "/{enum_version}/callable", method = RequestMethod.GET)
  public BaseResponseDTO<?> callable(@PathVariable("enum_version") EnumServiceVersion enumServiceVersion) {
    completableFutureService.callableRun(enumServiceVersion);
    return new BaseResponseDTO<>(enumServiceVersion);
  }

  @RequestMapping(value = "/{enum_version}/completable-future", method = RequestMethod.GET)
  public BaseResponseDTO<?> completableFuture(@PathVariable("enum_version") EnumServiceVersion enumServiceVersion) {
    completableFutureService.completableFutureRun(enumServiceVersion);
    return new BaseResponseDTO<>(enumServiceVersion);
  }

}
