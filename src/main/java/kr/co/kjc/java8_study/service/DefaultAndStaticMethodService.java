package kr.co.kjc.java8_study.service;

import kr.co.kjc.java8_study.defalut_and_static.DefaultFoo;
import kr.co.kjc.java8_study.defalut_and_static.DefaultAndStaticInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultAndStaticMethodService {

  /**
   * @implNote DefaultInterface에 있는 printNameUpperCase 메소드를 DefaultFoo에서 구현하지 않았는데도 실행이 된다.
   */
  public void defaultMethodRun() {
    DefaultAndStaticInterface defaultAndStaticInterface = new DefaultFoo("jaeChang");
    defaultAndStaticInterface.printName();
    defaultAndStaticInterface.printNameUpperCase();
  }

  /**
   * @implNote DefaultInterface에 있는 printNameUpperCase 메소드를 DefaultFoo에서 구현하지 않았는데도 실행이 된다.
   * 만약에 @Override해서 재 선언하면 해당 값으로 return 된다.
   */
  public void defaultMethodOverrideRun() {
    DefaultFoo defaultFoo = new DefaultFoo("jaeChang");
    defaultFoo.printName();
    defaultFoo.printNameUpperCase();
  }

  public void staticMethodRun() {
    DefaultAndStaticInterface defaultAndStaticInterface = new DefaultFoo("jaeChang");
    defaultAndStaticInterface.printName();

    DefaultAndStaticInterface.printAnything();
  }
}
