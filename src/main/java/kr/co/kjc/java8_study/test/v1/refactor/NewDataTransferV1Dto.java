package kr.co.kjc.java8_study.test.v1.refactor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kr.co.kjc.java8_study.test.EnumDataTransferType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
public class NewDataTransferV1Dto<T> {

//  private String chargePointId;
//  private String vendorId;
//  private EnumDataTransferType dataTransferType;
//  private List<MessageFields<T>> MessageFields;
//
//  @ToString
//  public static abstract class MessageFields<T> {
//
//    protected abstract List<MessageFields<T>> createMessageFields(MessageFields<T> clazz);
//
//  }
//
//  @Getter
//  @NoArgsConstructor(access = AccessLevel.PRIVATE)
//  @AllArgsConstructor(access = AccessLevel.PRIVATE)
//  @ToString
//  public static class ChargerMessageFields<T> extends NewDataTransferV1Dto.MessageFields<T> {
//
//    private String chargerModel;
//    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
//    private LocalDateTime timestamp;
//
//    @Override
//    protected List<T> createMessageFields(T clazz) {
//      List<T> result = new ArrayList<>();
//      result.add(clazz);
//      return result;
//    }
//
//    public static <T> ChargerMessageFields<T> of() {
//      ChargerMessageFields<T> clazz = new ChargerMessageFields<>();
//      clazz.chargerModel = "test-model";
//      clazz.timestamp = LocalDateTime.now();
//      return clazz;
//    }
//
//    public static <T> List<NewDataTransferV1Dto.MessageFields<T>> createList(ChargerMessageFields<T> clazz) {
//      ChargerMessageFields<T> messageFields = new ChargerMessageFields<>();
//      return messageFields.createMessageFields(clazz);
//    }
//
//  }
//
//  @Getter
//  @NoArgsConstructor(access = AccessLevel.PRIVATE)
//  @AllArgsConstructor(access = AccessLevel.PRIVATE)
//  @ToString
//  public static class MemberMessageFields<T> extends NewDataTransferV1Dto.MessageFields<T> {
//
//    private String uuid;
//    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
//    private LocalDateTime timestamp;
//
//    @Override
//    protected List<T> createMessageFields(T clazz) {
//      List<T> result = new ArrayList<>();
//      result.add(clazz);
//      return result;
//    }
//
//    public static <T> MemberMessageFields<T> of() {
//      MemberMessageFields<T> clazz = new MemberMessageFields<>();
//      clazz.uuid = UUID.randomUUID().toString();
//      clazz.timestamp = LocalDateTime.now();
//      return clazz;
//    }
//
//    public static <T> List<T> createList(T clazz) {
//      MemberMessageFields<T> messageFields = new MemberMessageFields<>();
//      return messageFields.createMessageFields(clazz);
//    }
//
//  }

}
