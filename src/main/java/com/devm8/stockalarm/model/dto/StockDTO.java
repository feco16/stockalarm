package com.devm8.stockalarm.model.dto;

public class StockDTO {

    private String stockUUID;
    private String stockName;
    private String symbol;
    private Double currentPrice;

    public String getStockUUID() {
        return stockUUID;
    }

    public void setStockUUID(String stockUUID) {
        this.stockUUID = stockUUID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
