package kr.co.kjc.java8_study.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import kr.co.kjc.java8_study.enums.EnumDataTransferType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@AllArgsConstructor
public class DataTransferV2Dto {

  private String chargePointId;
  private String vendorId;
  private EnumDataTransferType dataTransferType;
  private List<MessageFields> MessageFields;

  public interface MessageFields {

  }

  @Getter
  @ToString
  public static class ChargerMessageFields implements DataTransferV2Dto.MessageFields {
    private String chargerModel;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;

    public static List<DataTransferV2Dto.MessageFields> of() {
      ChargerMessageFields chargerMessageFields = new ChargerMessageFields();
      chargerMessageFields.chargerModel = "테스트 충전기";
      chargerMessageFields.timestamp = LocalDateTime.now();
      return List.of(chargerMessageFields);
    }

  }

  @Getter
  @ToString
  public static class MemberMessageFields implements DataTransferV2Dto.MessageFields {
    private String uuid;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;

    public static List<DataTransferV2Dto.MessageFields> of() {
      MemberMessageFields memberMessageFields = new MemberMessageFields();
      memberMessageFields.uuid = UUID.randomUUID().toString();
      memberMessageFields.timestamp = LocalDateTime.now();
      return List.of(memberMessageFields);
    }
  }

}
