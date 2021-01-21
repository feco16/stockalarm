package com.devm8.stockalarm.converter;

import com.devm8.stockalarm.client.ClientStrategyFactory;
import com.devm8.stockalarm.controller.MainController;
import com.devm8.stockalarm.dto.StockDTO;
import com.devm8.stockalarm.exception.CustomBadRequestException;
import com.devm8.stockalarm.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockDTOConverter implements Converter<Stock, StockDTO> {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private ClientStrategyFactory clientStrategyFactory;

    @Override
    public StockDTO convert(Stock source) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockUUID(source.getStockUUID());
        stockDTO.setStockName(source.getStockName());
        stockDTO.setSymbol(source.getSymbol());

        String price = "NaN";
        try {
//            price = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE).getPrice(source.getSymbol());
        } catch (CustomBadRequestException e) {
            logger.warn("Can't fetch the current price for {}", source.getSymbol());
        }

        stockDTO.setPrice(price);
        return stockDTO;
    }
}
