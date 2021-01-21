package com.devm8.stockalarm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

    @Column(name = "SAVED_PRICE")
    private Double savedPrice;

    @Column(name = "TARGET_PERCENTAGE")
    private Double targetPercentage;

    @Column(name = "ACTUAL_PERCENTAGE")
    private Double actualPercentage;

    @Column(name = "STATUS")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private StockUser stockUser;

    @ManyToOne
    @JoinColumn(name = "stock_id")
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

    public Double getSavedPrice() {
        return savedPrice;
    }

    public void setSavedPrice(Double savedPrice) {
        this.savedPrice = savedPrice;
    }

    public Double getTargetPercentage() {
        return targetPercentage;
    }

    public void setTargetPercentage(Double targetPercentage) {
        this.targetPercentage = targetPercentage;
    }

    public Double getActualPercentage() {
        return actualPercentage;
    }

    public void setActualPercentage(Double actualPercentage) {
        this.actualPercentage = actualPercentage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
