package com.devm8.stockalarm.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
@SequenceGenerator(name = "seq_stock", sequenceName = "seq_stock", allocationSize = 1)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_stock")
    @Column(name = "STOCK_ID")
    private long stockId;

    @Column(name = "STOCK_UUID")
    private String stockUUID;

    @Column(name = "STOCK_NAME")
    private String stockName;

    @Column(name = "SYMBOL")
    private String symbol;

    @Column(name = "CURRENT_PRICE")
    private Double currentPrice;

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

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stockUUID='" + stockUUID + '\'' +
                ", stockName='" + stockName + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
