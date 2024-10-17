package kr.co.kjc.java8_study.test.v2;

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
import kr.co.kjc.java8_study.test.v2.DataTransferV2Dto.MessageFields;
import kr.co.kjc.java8_study.test.EnumDataTransferType;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDataTransferV2Service {

  public DataTransferV2Dto get(EnumDataTransferType enumDataTransferType) {
    List<MessageFields> messageFields = convertMessageFields(enumDataTransferType);
    DataTransferV2Dto result = new DataTransferV2Dto("99999806", "test-vendor", enumDataTransferType, messageFields);
    return result;
  }

  public JSONObject getJson(EnumDataTransferType enumDataTransferType)
      throws JsonProcessingException, ParseException {
    ObjectMapper om = getObjectMapper();
    JSONParser jsonParser = new JSONParser();

    List<MessageFields> messageFields = convertMessageFields(enumDataTransferType);
    DataTransferV2Dto result = new DataTransferV2Dto("99999806", "test-vendor", enumDataTransferType, messageFields);
    String json = om.writeValueAsString(result);

    return (JSONObject) jsonParser.parse(json);
  }

  private static List<DataTransferV2Dto.MessageFields> convertMessageFields(EnumDataTransferType enumDataTransferType) {
    return TestMessageFieldsFactoryProvider.get(enumDataTransferType).createMessageFields();
  }

  private ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.registerModule(javaTimeModule());
//    objectMapper.registerModule(problemDetailModule());
    return objectMapper;
  }

  private JavaTimeModule javaTimeModule() {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    javaTimeModule.addSerializer(
        new LocalDateSerializer(DateTimeFormatter.ISO_LOCAL_DATE));
    javaTimeModule.addSerializer(
        new LocalDateTimeSerializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    javaTimeModule.addDeserializer(LocalDate.class,
        new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
    javaTimeModule.addDeserializer(LocalDateTime.class,
        new LocalDateTimeDeserializer(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    return javaTimeModule;
  }

}
