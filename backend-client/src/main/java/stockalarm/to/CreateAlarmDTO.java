package stockalarm.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateAlarmDTO {

    private String alarmUUID;
    private String alarmName;
    @NotNull
    private Long stockId;
    @NotNull
    private Long userId;
    private Double currentPrice;
    private Double savedPrice;
    private Double targetPercentage;
    private Double actualPercentage;
    private Boolean status;
}
