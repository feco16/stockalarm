package stockalarm.to;

import lombok.Data;

@Data
public class StockDTO {

    private String stockUUID;
    private String stockName;
    private String symbol;
    private Double currentPrice;

}
