package com.devm8.stockalarm.clients.stockapi;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public interface ClientStrategy {

    /**
     * Returns the latest price for the given stock symbol
     * @param symbol - Unique series of letters representing a stock
     * @return {@link Double} number
     */
    Double getPrice(String symbol);

    Map<String, String> getPrices(List<String> symbols);

    ClientEnum getStrategyName();

    default WebClient getWebClient(String baseURL) {
        WebClient client = WebClient
                .builder()
                .baseUrl(baseURL)
                .build();
        return client;
    }

}
