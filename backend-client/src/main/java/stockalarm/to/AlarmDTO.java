package stockalarm.to;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AlarmDTO {

    private String alarmUUID;
    private String alarmName;
    private String symbol;
    private Double currentPrice;
    private Double savedPrice;
    private Double targetPercentage;
    private Double actualPercentage;
    private Boolean status;

}
