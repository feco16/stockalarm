package com.devm8.stockalarm.client;

import com.devm8.stockalarm.exception.CustomBadRequestException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Component
public class ClientAlphavantage implements ClientStrategy{

    String BASE_URL = "https://www.alphavantage.co/query";
    String API_KEY = "I5TPWWHJJDCQ47IZ";

    enum Interval {
        MIN_1("1min");

        public final String value;

        Interval(String value) {
            this.value = value;
        }
    }

    enum StockPrice {
        OPEN("1. open");

        public final String value;

        StockPrice(String value) {
            this.value = value;
        }
    }

    @Override
    public ClientEnum getStrategyName() {
        return ClientEnum.ALPHAVANTAGE;
    }

    @Override
    public String getPrice(String symbol) {
        JSONObject jsonObject = getWebClient()
                .get().uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", Interval.MIN_1.value)
                        .queryParam("outputsize", "compact")
                        .queryParam("apikey", API_KEY)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        return parseCurrentPrice(jsonObject, Interval.MIN_1);

    }

    private String parseCurrentPrice(JSONObject jsonObject, Interval interval) {
        if (jsonObject == null) {
            throw new CustomBadRequestException("");
        }
        return getFirstEntry((HashMap<String, HashMap>) jsonObject.get("Time Series (" + interval.value + ")"))
                .get(StockPrice.OPEN.value);
    }

    private HashMap<String, String> getFirstEntry(HashMap<String, HashMap> hashMap) {
        if (null == hashMap) {
            throw new CustomBadRequestException("");
        }
        return hashMap.entrySet().iterator().next().getValue();
    }

    private WebClient getWebClient() {
        WebClient client = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .build();
        return client;
    }
}
