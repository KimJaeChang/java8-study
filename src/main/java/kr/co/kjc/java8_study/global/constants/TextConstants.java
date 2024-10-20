package kr.co.kjc.java8_study.global.constants;

import kr.co.kjc.java8_study.global.utils.CommonUtils;

/**
 * 텍스트 메시지에 관한 상수 모음
 */
public class TextConstants {

  public static final String EXCEPTION_PREFIX = "Exception Occurred!!";

  public static final String EXECUTE_TIME_CHECK_MESSAGE = "수행 시간 : {} (s)";

  public static final String LOGGING_PREFIX_START = "Log ==> [{}.{}] START ";

  public static final String LOGGING_PREFIX_END = "Log ==> [{}.{}] END ";

  public static final String LOGGING_PREFIX_REQUEST = "REQUEST : [{}]";

  public static final String LOGGING_PREFIX_RESPONSE = "RESPONSE : [{}]";

  public static final String LOGGING_FORMAT = """ 
      [REQUEST] {} - {} {} - {} ms
      {}
      [RESPONSE] {}
      """;

  public static final String LOGGING_METHOD_FORMAT = CommonUtils.getCurrentMethodName() + " : ";
}
