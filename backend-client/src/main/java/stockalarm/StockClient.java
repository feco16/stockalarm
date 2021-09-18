package stockalarm;

import org.springframework.stereotype.Component;
import stockalarm.to.StockDTO;

import java.util.List;

@Component
public class StockClient {

    public void createStock(final StockDTO stockDTO) {

    }

    public List<StockDTO> getAllStocks() {
        return List.of();
    }
}
