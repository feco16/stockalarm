package com.devm8.stockalarm.model.dto;

import lombok.Data;

@Data
public class StockDTO {

    private String stockUUID;
    private String stockName;
    private String symbol;
    private Double currentPrice;

}
