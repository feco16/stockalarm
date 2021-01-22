package com.devm8.stockalarm.clients.stockapi.alphavantage;

import com.devm8.stockalarm.clients.stockapi.ClientEnum;
import com.devm8.stockalarm.clients.stockapi.ClientStrategy;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class Alphavantage implements ClientStrategy {

    String url = "https://www.alphavantage.co/query";
    String apiKey = "I5TPWWHJJDCQ47IZ";

    private static final Logger logger = LoggerFactory.getLogger(Alphavantage.class);

    enum Interval {
        MIN_1("1min");

        public final String value;

        Interval(String value) {
            this.value = value;
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public ClientEnum getStrategyName() {
        return ClientEnum.ALPHAVANTAGE;
    }

    @Override
    public Double getPrice(String symbol) {
        JSONObject jsonObject = WebClient
                .builder()
                .baseUrl(url)
                .build()
                .get().uri(uriBuilder -> uriBuilder
                        .queryParam("function", "TIME_SERIES_INTRADAY")
                        .queryParam("symbol", symbol)
                        .queryParam("interval", Interval.MIN_1.value)
                        .queryParam("outputsize", "compact")
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve().bodyToMono(JSONObject.class).block();
        AlphavantageResponse response = new AlphavantageResponse(jsonObject, Interval.MIN_1);
        return response.isSucces() ? response.getCurrentPrice() : 0.;
    }

    @Override
    public Map<String, String> getPrices(List<String> symbols) {
        return null;
    }

}
