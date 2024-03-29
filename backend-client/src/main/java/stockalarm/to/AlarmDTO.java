package stockalarm.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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
