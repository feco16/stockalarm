package com.devm8.stockalarm.clients.stockapi;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.exception.CustomBadRequestException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ClientAlphavantage implements ClientStrategy {

    String BASE_URL = "https://www.alphavantage.co/query";
    String API_KEY = "I5TPWWHJJDCQ47IZ";

    private static final Logger logger = LoggerFactory.getLogger(ClientAlphavantage.class);

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
    public Double getPrice(String symbol) {
        JSONObject jsonObject = getWebClient()
                .get().uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", Interval.MIN_1.value)
                        .queryParam("outputsize", "compact")
                        .queryParam("apikey", API_KEY)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        return Utils.formatDouble(parseCurrentPrice(jsonObject, Interval.MIN_1));

    }

    @Override
    public Map<String, String> getPrices(List<String> symbols) {
        return null;
    }

    private Double parseCurrentPrice(JSONObject jsonObject, Interval interval) {
        if (jsonObject == null) {
            throw new CustomBadRequestException("");
        }
        Double value = 0.;
        try {
            value = Double.valueOf(getFirstEntry((HashMap<String, HashMap>) jsonObject.get("Time Series (" + interval.value + ")"))
                    .get(StockPrice.OPEN.value));
        } catch (NumberFormatException e) {
            logger.warn("The stock value for symbol is not a number");
        }

        return value;
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
