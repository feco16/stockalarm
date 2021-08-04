package com.devm8.stockalarm.service;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.clients.stockapi.ClientEnum;
import com.devm8.stockalarm.clients.stockapi.ClientStrategy;
import com.devm8.stockalarm.clients.stockapi.ClientStrategyFactory;
import com.devm8.stockalarm.model.converter.StockConverter;
import com.devm8.stockalarm.model.converter.StockDTOConverter;
import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.model.entity.Stock;
import com.devm8.stockalarm.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        stockRepository.save(stockConverter.convert(stockDTO));
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
