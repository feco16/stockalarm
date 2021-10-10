package stockalarm.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAlarmDTO {

    private String alarmName;
    private Long stockId;
    private Long userId;
    private Double currentPrice;
    private Double savedPrice;
    private Double targetPercentage;
    private Double actualPercentage;
    private Boolean status;
}
