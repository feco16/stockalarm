package com.devm8.stockalarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALARM")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ALARM_ID")
    private long alarmId;

    @Column(name = "ALARM_UUID")
    private String alarmUUID;

    @Column(name = "ALARM_NAME")
    private String alarmName;

    @Column(name = "CURRENT_PRICE")
    private long currentPrice;

    @Column(name = "PERCENTAGE")
    private long percentage;

    @Column(name = "STATUS")
    private boolean status;

    @ManyToOne
    private StockUser stockUser;

    @ManyToOne
    private Stock stock;

    public long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(long alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmUUID() {
        return alarmUUID;
    }

    public void setAlarmUUID(String alarmUUID) {
        this.alarmUUID = alarmUUID;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public long getPercentage() {
        return percentage;
    }

    public void setPercentage(long percentage) {
        this.percentage = percentage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public StockUser getStockUser() {
        return stockUser;
    }

    public void setStockUser(StockUser stockUser) {
        this.stockUser = stockUser;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}