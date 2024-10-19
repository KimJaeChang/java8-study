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

  @RequestMapping(value = "/{enum_version}/completableFutureService", method = RequestMethod.GET)
  public BaseResponseDTO<?> completableFuture(@PathVariable("enum_version") EnumServiceVersion enumServiceVersion)
      throws InterruptedException {
    completableFutureService.run(enumServiceVersion);
    return new BaseResponseDTO<>(enumServiceVersion);
  }

}
