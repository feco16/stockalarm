package stockalarm.clients.stockapi;

import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

public interface ClientStrategy {

    /**
     * Returns the latest price for the given stock symbol.
     *
     * @param symbol - Unique series of letters representing a stock
     * @return {@link Double} number
     */
    Double getPrice(final String symbol);

    Map<String, String> getPrices(final List<String> symbols);

    ClientEnum getStrategyName();

    default WebClient getWebClient(final String baseUrl) {
        return WebClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }

}
