package com.devm8.stockalarm.dto;

import javax.persistence.Column;

public class StockDTO {

    private String stockUUID;
    private String stockName;
    private String symbol;
    private String price;

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
