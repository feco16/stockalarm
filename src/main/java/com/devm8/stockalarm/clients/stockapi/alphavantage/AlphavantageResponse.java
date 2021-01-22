package com.devm8.stockalarm.clients.stockapi.alphavantage;

import com.devm8.stockalarm.Utils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class AlphavantageResponse {

    private static final Logger logger = LoggerFactory.getLogger(AlphavantageResponse.class);

    private Map metaData;
    private Map timeSeries;
    private boolean isSucces;

    private enum StockPrice {
        OPEN("1. open");

        public final String value;

        StockPrice(String value) {
            this.value = value;
        }
    }

    public AlphavantageResponse(JSONObject jsonObject, Alphavantage.Interval interval) {
        this.metaData = (Map) jsonObject.get("Meta Data");
        this.timeSeries = (Map) jsonObject.get(String.format("Time Series (%s)", interval.value));
        this.isSucces = (null != this.metaData && null != this.timeSeries);
    }

    public boolean isSucces() {
        return isSucces;
    }

    public Double getCurrentPrice() {
        return Utils.formatDouble(parseCurrentPrice());
    }

    private Double parseCurrentPrice() {
        Double value = 0.;
        try {
            Object price = getFirstEntry(timeSeries).get(StockPrice.OPEN.value);
            if (null != price) {
                value = Double.valueOf(price.toString());
            }
        } catch (NumberFormatException e) {
            logger.warn("The stock value for symbol is not a number");
        }
        return value;
    }


    private Map<String, String> getFirstEntry(Map<String, Map> map) {
        return (HashMap<String, String>) map.entrySet().iterator().next().getValue();
    }

}
