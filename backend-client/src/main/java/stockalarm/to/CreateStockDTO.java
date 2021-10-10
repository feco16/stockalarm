package stockalarm.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateStockDTO {

    @NotNull
    private String stockName;
    private String symbol;
    private Double currentPrice;
}
