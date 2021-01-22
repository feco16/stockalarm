package com.devm8.stockalarm.service;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.clients.stockapi.ClientEnum;
import com.devm8.stockalarm.clients.stockapi.ClientStrategy;
import com.devm8.stockalarm.clients.stockapi.ClientStrategyFactory;
import com.devm8.stockalarm.exception.CustomBadRequestException;
import com.devm8.stockalarm.model.converter.StockConverter;
import com.devm8.stockalarm.model.converter.StockDTOConverter;
import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.model.entity.Stock;
import com.devm8.stockalarm.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockDTOConverter stockDTOConverter;

    @Autowired
    private StockConverter stockConverter;

    @Autowired
    private ClientStrategyFactory clientStrategyFactory;

    public List<StockDTO> getAllStocks() {
        List<StockDTO> stockDTOList = new ArrayList<>();
        stockRepository.findAll().forEach(
                s -> stockDTOList.add(stockDTOConverter.convert(s))
        );
        return stockDTOList;
    }

    public void createStock(StockDTO stockDTO) {
        stockRepository.save(stockConverter.convert(stockDTO));
    }

    public void actualizePrices() {
        ClientStrategy strategy = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE);
        stockRepository.findAll().forEach(stock -> handleStock(stock, strategy));
    }

    private void handleStock(Stock stock, ClientStrategy strategy) {
        Double price = 0.;
        try {
//            price = stock.getCurrentPrice() + 1;
            price = strategy.getPrice(stock.getSymbol());
        } catch (CustomBadRequestException e) {
            logger.warn("Can't get the current price for {}", stock.getSymbol());
        }
        if (Utils.compareDouble(price, stock.getCurrentPrice())) {
            return;
        }
        stock.setCurrentPrice(price);
        stockRepository.save(stock);
    }

}
