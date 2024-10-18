package kr.co.kjc.java8_study.service;

import java.util.Calendar;
import java.util.Date;
import kr.co.kjc.java8_study.enums.EnumJavaVersion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DateTimeService {

  public void run(EnumJavaVersion enumJavaVersion) {
    boolean isBeforeVersion8 = EnumJavaVersion.isBeforeVersion8(enumJavaVersion);
    if(isBeforeVersion8) {
      beforeRun();
    } else {
      afterRun();
    }
  }

  private void beforeRun() {
    Date date = new Date();
    long time = date.getTime();
    Calendar calendar = Calendar.getInstance();
    System.out.println("date : " + date);
    System.out.println("time : " + time);
    System.out.println("calendar : " + calendar.getTime());
  }

  private void afterRun() {

  }

}
