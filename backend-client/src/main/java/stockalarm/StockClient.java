package stockalarm;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import stockalarm.to.StockDTO;

import java.util.List;

@Component
public class StockClient {

    String url = "localhost:8080/stocks";

    public void createStock(final StockDTO stockDTO) {
        WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .body(Mono.just(stockDTO), StockDTO.class)
                .retrieve().toBodilessEntity().block();
    }

    public List<StockDTO> getAllStocks() {
        return WebClient
                .builder()
                .baseUrl(url)
                .build()
                .get().uri(uriBuilder -> uriBuilder
                        .build())
                .retrieve().bodyToMono(List.class).block();
    }
}
