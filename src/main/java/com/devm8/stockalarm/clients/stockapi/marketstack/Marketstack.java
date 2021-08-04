package com.devm8.stockalarm.clients.stockapi.marketstack;

import com.devm8.stockalarm.clients.stockapi.ClientEnum;
import com.devm8.stockalarm.clients.stockapi.ClientStrategy;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Marketstack implements ClientStrategy {

    String url = "http://api.marketstack.com/v1/intraday/latest";
    String apiKey = "1539d784940b91ea3126a6a34ff49ad3";

    @Override
    public ClientEnum getStrategyName() {
        return ClientEnum.MARKETSTOCK;
    }

    @Override
    public Double getPrice(final String symbol) {
        return 0d;
    }

    @Override
    public Map<String, String> getPrices(final List<String> symbols) {
        final JSONObject jsonObject = getWebClient(url)
                .get().uri(uriBuilder -> uriBuilder
                        .queryParam("limit", 1)
                        .queryParam("symbols", concatSymbols(symbols))
                        .queryParam("interval", "1min")
                        .queryParam("access_key", apiKey)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        return parseCurrentPrice(jsonObject, symbols);
    }

    private Map<String, String> parseCurrentPrice(final JSONObject jsonObject, final List<String> symbols) {
        // TODO
        return null;
    }

    private String concatSymbols(final List<String> symbols) {
        return String.join(",", symbols);
    }

}
