package stockalarm.clients.stockapi.alphavantage;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import stockalarm.clients.stockapi.ClientEnum;
import stockalarm.clients.stockapi.ClientStrategy;
import stockalarm.utilities.Constants;

import java.util.List;
import java.util.Map;

@Component
public class Alphavantage implements ClientStrategy {

    // TODO move values in configuration file
    String url = "https://www.alphavantage.co/query";
    final String apiKey = "I5TPWWHJJDCQ47IZ";

    enum Interval {
        MIN_1("1min");

        public final String value;

        Interval(final String value) {
            this.value = value;
        }
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public ClientEnum getStrategyName() {
        return ClientEnum.ALPHAVANTAGE;
    }

    @Override
    public Double getPrice(final String symbol) {
        final JSONObject jsonObject = WebClient
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
        final AlphavantageResponse response = new AlphavantageResponse(jsonObject, Interval.MIN_1);
        return response.isSucces() ? response.getCurrentPrice() : Constants.DOUBLE_ZERO;
    }

    @Override
    public Map<String, String> getPrices(final List<String> symbols) {
        return null;
    }

}
