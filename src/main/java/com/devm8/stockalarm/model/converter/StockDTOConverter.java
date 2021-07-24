package com.devm8.stockalarm.model.converter;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.clients.stockapi.ClientStrategyFactory;
import com.devm8.stockalarm.exception.CustomBadRequestException;
import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.model.entity.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StockDTOConverter implements Converter<Stock, StockDTO> {

    @Autowired
    private ClientStrategyFactory clientStrategyFactory;

    @Override
    public StockDTO convert(Stock source) {
        final StockDTO stockDTO = new StockDTO();
        stockDTO.setStockUUID(source.getStockUUID());
        stockDTO.setStockName(source.getStockName());
        stockDTO.setSymbol(source.getSymbol());

        final String price = "NaN";
        try {
//            price = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE).getPrice(source.getSymbol());
        } catch (CustomBadRequestException e) {
            log.warn("Can't fetch the current price for {}", source.getSymbol());
        }

        stockDTO.setCurrentPrice(Utils.formatDouble(source.getCurrentPrice()));
        return stockDTO;
    }
}
