package com.devm8.stockalarm.client.impl;

import com.devm8.stockalarm.client.ClientEnum;
import com.devm8.stockalarm.client.ClientStrategy;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class ClientMarketstack implements ClientStrategy {

    String BASE_URL = "http://api.marketstack.com/v1/intraday/latest";
    String API_KEY = "1539d784940b91ea3126a6a34ff49ad3";

    @Override
    public ClientEnum getStrategyName() {
        return ClientEnum.MARKETSTOCK;
    }

    @Override
    public String getPrice(String symbol) {
        return null;
    }

    @Override
    public Map<String, String> getPrices(List<String> symbols) {
        JSONObject jsonObject = getWebClient()
                .get().uri(uriBuilder -> uriBuilder
                        .queryParam("limit", 1)
                        .queryParam("symbols", concatSymbols(symbols))
                        .queryParam("interval", ClientAlphavantage.Interval.MIN_1.value)
                        .queryParam("access_key", API_KEY)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        return parseCurrentPrice(jsonObject, symbols);
    }

    private Map<String, String>  parseCurrentPrice(JSONObject jsonObject, List<String> symbols) {
        // TODO
        return null;
    }

    private String concatSymbols(List<String> symbols) {
        return String.join( ",", symbols);
    }

    private WebClient getWebClient() {
        WebClient client = WebClient
                .builder()
                .baseUrl(BASE_URL)
                .build();
        return client;
    }
}
