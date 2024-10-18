package kr.co.kjc.java8_study.service;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    System.out.println("\t");
    System.out.println("date : " + date);
    System.out.println("time : " + time);
    System.out.println("calendar : " + calendar.getTime());
    System.out.println("\t");

    Instant now = Instant.now();  // 현재 UTC (GMT)를 리턴한다
    System.out.println("\t");
    System.out.println("UTC now : " + now);
    System.out.println("\t");

    ZonedDateTime seoul = now.atZone(ZoneId.of("Asia/Seoul"));
    System.out.println("\t");
    System.out.println("Seoul now : " + seoul);
    System.out.println("\t");

    ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
    System.out.println("\t");
    System.out.println("zonedDateTime : " + zonedDateTime);
    System.out.println("\t");
  }

  private void afterRun() {
    LocalDateTime nowDateTime = LocalDateTime.now();
    LocalDateTime birthDay = LocalDateTime.of(1982, Month.JANUARY, 14, 0, 0, 0);
    ZonedDateTime nowKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    System.out.println("\t");
    System.out.println("UTC nowDateTime : " + nowDateTime);
    System.out.println("birthDay : " + birthDay);
    System.out.println("nowKorea : " + nowKorea);
    System.out.println("\t");

    Instant nowInstant = nowKorea.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toInstant();
    ZonedDateTime zonedDateTime = nowKorea.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toOffsetDateTime().toZonedDateTime();
    System.out.println("\t");
    System.out.println("nowInstant : " + nowInstant);
    System.out.println("zonedDateTime : " + zonedDateTime);
    System.out.println("\t");

    LocalDate nowLocalDate = nowKorea.toLocalDate();
    LocalDate thisYearBirthday = LocalDate.of(2020, Month.JANUARY, 14);

    System.out.println("\t");
    System.out.println("nowLocalDate : " + nowLocalDate);
    System.out.println("thisYearBirthday : " + thisYearBirthday);
    System.out.println("\t");

    Period period = Period.between(nowLocalDate, thisYearBirthday);

    System.out.println("\t");
    System.out.println("차이나는 개월수 계산 : " + period.get(ChronoUnit.MONTHS));
    System.out.println("차이나는 일수 계산 : " + period.get(ChronoUnit.DAYS));
    System.out.println("\t");

    Instant secondsPlusBy10 = nowInstant.plus(10, ChronoUnit.SECONDS);
    Duration betweenBy10 = Duration.between(nowInstant, secondsPlusBy10);
    System.out.println("\t");
    System.out.println("현재로부터 10초뒤 : " + secondsPlusBy10);
    System.out.println("시간차이 10초 : " + betweenBy10.getSeconds());
    System.out.println("\t");

    String isoDateTimeFormat = nowKorea.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    String customFormat = nowKorea.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssSSS"));
    LocalDate parseLocalDate = LocalDate.parse(isoDateTimeFormat, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    System.out.println("\t");
    System.out.println("isoDateTimeFormat : " + isoDateTimeFormat);
    System.out.println("customFormat : " + customFormat);
    System.out.println("parseLocalDate : " + parseLocalDate);
    System.out.println("\t");


    Date nowDate = new Date(nowKorea.getYear(), nowKorea.getMonthValue(), nowKorea.getDayOfMonth(), nowKorea.getHour(), nowKorea.getMinute(), nowKorea.getSecond());
    ZonedDateTime nowZonedDateTime = nowDate.toInstant().atZone(ZoneId.of("Asia/Seoul"));
    System.out.println("\t");
    System.out.println("nowDate : " + nowDate);
    System.out.println("nowZonedDateTime : " + nowZonedDateTime);
    System.out.println("\t");

    GregorianCalendar gregorianCalendar = new GregorianCalendar();
    ZonedDateTime gregorianCalendarZonedDateTime = gregorianCalendar.toInstant().atZone(ZoneId.of("Asia/Seoul"));
    GregorianCalendar gregorianCalendarFrom = GregorianCalendar.from(gregorianCalendarZonedDateTime);
    System.out.println("\t");
    System.out.println("gregorianCalendar : " + gregorianCalendar);
    System.out.println("gregorianCalendarZonedDateTime : " + gregorianCalendarZonedDateTime);
    System.out.println("gregorianCalendarFrom : " + gregorianCalendarFrom);
    System.out.println("\t");

    System.out.println("\t");
    System.out.println("plusHours : " + nowKorea.plus(12, ChronoUnit.HOURS));
    System.out.println("plusMonth : " + nowKorea.plus(12, ChronoUnit.MONTHS));
    System.out.println("minusHours : " + nowKorea.minus(12, ChronoUnit.HOURS));
    System.out.println("minusMonth : " + nowKorea.minus(12, ChronoUnit.MONTHS));
    System.out.println("\t");
  }

}
