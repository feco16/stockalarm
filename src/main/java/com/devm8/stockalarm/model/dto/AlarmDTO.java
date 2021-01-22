package com.devm8.stockalarm.model.dto;

public class AlarmDTO {

    private String alarmUUID;
    private String alarmName;
    private String symbol;
    private Double currentPrice;
    private Double savedPrice;
    private Double targetPercentage;
    private Double actualPercentage;
    private Boolean status;

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

    @Override
    public String toString() {
        return "AlarmDTO{" +
                "alarmUUID='" + alarmUUID + '\'' +
                ", alarmName='" + alarmName + '\'' +
                ", symbol='" + symbol + '\'' +
                ", currentPrice=" + currentPrice +
                ", savedPrice=" + savedPrice +
                ", targetPercentage=" + targetPercentage +
                ", actualPercentage=" + actualPercentage +
                ", status=" + status +
                '}';
    }
}
