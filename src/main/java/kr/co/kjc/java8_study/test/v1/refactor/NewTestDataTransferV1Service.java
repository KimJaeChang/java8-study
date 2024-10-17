package kr.co.kjc.java8_study.test.v1.refactor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import kr.co.kjc.java8_study.test.EnumDataTransferType;
import kr.co.kjc.java8_study.test.v1.DataTransferV1Dto;
import kr.co.kjc.java8_study.test.v1.DataTransferV1Dto.MessageFields;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewTestDataTransferV1Service {

//  public DataTransferV1Dto get(EnumDataTransferType enumDataTransferType) {
//    List<? extends MessageFields<?>> messageFields = convertMessageFields(enumDataTransferType);
//    DataTransferV1Dto result = new DataTransferV1Dto("99999806", "test-vendor", enumDataTransferType, messageFields);
//    return result;
//  }
//
//  public JSONObject getJson(EnumDataTransferType enumDataTransferType)
//      throws JsonProcessingException, ParseException {
//    ObjectMapper om = getObjectMapper();
//    JSONParser jsonParser = new JSONParser();
//
//    List<? extends MessageFields<?>> messageFields = convertMessageFields(enumDataTransferType);
//
////    ArrayList<Map<String, Object>> messageFields = new ArrayList<Map<String, Object>>();
////    Map<String, Object> messageField = new HashMap<>();
////    messageField.put("chargerModel", "테스트 충전기");
////    messageField.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
////    messageFields.add(messageField);
////
////    List<?> covertMessageFields = om.convertValue(messageFields, List.class);
//
//    DataTransferV1Dto<?> result = new DataTransferV1Dto<>("99999806", "test-vendor", enumDataTransferType, messageFields);
//    String json = om.writeValueAsString(result);
//
//    return (JSONObject) jsonParser.parse(json);
//  }
//
//  private static List<? extends MessageFields<?>> convertMessageFields(EnumDataTransferType enumDataTransferType) {
//    switch (enumDataTransferType) {
//      case CHARGER -> {
//        ChargerMessageFields<?> messageFields = ChargerMessageFields.of();
//        return ChargerMessageFields.createList(messageFields);
//      }
//      case MEMBER -> {
//        MemberMessageFields<?> messageFields = MemberMessageFields.of();
//        return MemberMessageFields.createList(messageFields);
//      }
//      default -> {
//        throw new RuntimeException("올바르지 않은 EnumDataTransferType 입니다.");
//      }
//    }
//  }
//
//  private ObjectMapper getObjectMapper() {
//    ObjectMapper objectMapper = new ObjectMapper();
//    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//    objectMapper.registerModule(javaTimeModule());
////    objectMapper.registerModule(problemDetailModule());
//    return objectMapper;
//  }
//
//  private JavaTimeModule javaTimeModule() {
//    JavaTimeModule javaTimeModule = new JavaTimeModule();
//    javaTimeModule.addSerializer(
//        new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
//    javaTimeModule.addSerializer(
//        new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    javaTimeModule.addDeserializer(LocalDate.class,
//        new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
//    javaTimeModule.addDeserializer(LocalDateTime.class,
//        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//    return javaTimeModule;
//  }
}
