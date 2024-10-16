package kr.co.kjc.java8_study.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.co.kjc.java8_study.domain.jpa.Member;
import kr.co.kjc.java8_study.repository.jpa.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MethodReferenceService {

  private final MemberJpaRepository memberJpaRepository;

  public List<String> run() {
    return memberJpaRepository.findAll().stream()
        .map(Member::getUserName)
        .collect(Collectors.toList());
  }

}
