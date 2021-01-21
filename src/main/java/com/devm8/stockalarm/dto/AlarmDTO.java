package com.devm8.stockalarm.dto;

public class AlarmDTO {

    private String alarmUUID;
    private String alarmName;
    private String symbol;
    private long currentPrice;
    private long percentage;
    private boolean status;

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
}
