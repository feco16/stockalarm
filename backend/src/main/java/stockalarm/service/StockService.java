package stockalarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stockalarm.utilities.Utils;
import stockalarm.clients.stockapi.ClientEnum;
import stockalarm.clients.stockapi.ClientStrategy;
import stockalarm.clients.stockapi.ClientStrategyFactory;
import stockalarm.model.converter.StockConverter;
import stockalarm.model.entity.Stock;
import stockalarm.repository.StockRepository;
import stockalarm.to.CreateStockDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final ClientStrategyFactory clientStrategyFactory;
    private final StockRepository stockRepository;
    private final StockConverter stockConverter;

    public void createStock(final CreateStockDTO stockDTO) {
        final Stock stock = stockConverter.convert(stockDTO);
        stockRepository.save(stock);
    }

    public void actualizePrices() {
        final ClientStrategy strategy = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE);
        stockRepository.findAll().stream()
                .filter(stock -> stock.getSymbol() != null)
                .forEach(stock -> handleStock(stock, strategy));
    }

    private void handleStock(final Stock stock, final ClientStrategy strategy) {
        final Double price = strategy.getPrice(stock.getSymbol());

        if (Utils.compareDouble(price, stock.getCurrentPrice())) {
            return;
        }
        stock.setCurrentPrice(price);
        stockRepository.save(stock);
    }

}
