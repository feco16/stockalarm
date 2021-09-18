package stockalarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stockalarm.Utils;
import stockalarm.clients.stockapi.ClientEnum;
import stockalarm.clients.stockapi.ClientStrategy;
import stockalarm.clients.stockapi.ClientStrategyFactory;
import stockalarm.model.converter.StockConverter;
import stockalarm.model.converter.StockDTOConverter;
import stockalarm.model.entity.Stock;
import stockalarm.repository.StockRepository;
import stockalarm.to.StockDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockService {

    private final StockRepository stockRepository;
    private final StockDTOConverter stockDTOConverter;
    private final StockConverter stockConverter;
    private final ClientStrategyFactory clientStrategyFactory;

    public List<StockDTO> getAllStocks() {
        final List<StockDTO> stockDTOList = new ArrayList<>();
        stockRepository.findAll().forEach(
                s -> stockDTOList.add(stockDTOConverter.convert(s))
        );
        return stockDTOList;
    }

    public void createStock(final StockDTO stockDTO) {
        stockRepository.save(Objects.requireNonNull(stockConverter.convert(stockDTO)));
    }

    public void actualizePrices() {
        final ClientStrategy strategy = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE);
        stockRepository.findAll().forEach(stock -> handleStock(stock, strategy));
    }

    private void handleStock(final Stock stock, final ClientStrategy strategy) {
        // Double price = stock.getCurrentPrice() + 1;
        final Double price = strategy.getPrice(stock.getSymbol());
        if (Utils.compareDouble(price, stock.getCurrentPrice())) {
            return;
        }
        stock.setCurrentPrice(price);
        stockRepository.save(stock);
    }

}
