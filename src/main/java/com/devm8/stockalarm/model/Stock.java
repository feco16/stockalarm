package com.devm8.stockalarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STOCK_ID")
    private long stockId;

    @Column(name = "STOCK_UUID")
    private String stockUUID;

    @Column(name = "SYMBOL")
    private String symbol;

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public String getStockUUID() {
        return stockUUID;
    }

    public void setStockUUID(String stockUUID) {
        this.stockUUID = stockUUID;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stockUUID='" + stockUUID + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
