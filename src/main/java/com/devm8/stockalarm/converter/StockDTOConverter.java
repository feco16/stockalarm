package com.devm8.stockalarm.converter;

import com.devm8.stockalarm.client.ClientStrategyFactory;
import com.devm8.stockalarm.client.ClientEnum;
import com.devm8.stockalarm.dto.StockDTO;
import com.devm8.stockalarm.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StockDTOConverter implements Converter<Stock, StockDTO> {

    @Autowired
    private ClientStrategyFactory clientStrategyFactory;

    @Override
    public StockDTO convert(Stock source) {
        StockDTO stockDTO = new StockDTO();
        stockDTO.setStockUUID(source.getStockUUID());
        stockDTO.setStockName(source.getStockName());
        stockDTO.setSymbol(source.getSymbol());
        stockDTO.setPrice(clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE).getPrice(source.getSymbol()));
        return stockDTO;
    }
}
