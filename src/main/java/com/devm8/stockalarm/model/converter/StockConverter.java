package com.devm8.stockalarm.model.converter;


import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.model.entity.Stock;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StockConverter implements Converter<StockDTO, Stock> {

    @Override
    public Stock convert(StockDTO source) {
        Stock stock = new Stock();
        stock.setStockUUID(UUID.randomUUID().toString());
        stock.setStockName(source.getStockName());
        stock.setSymbol(source.getSymbol());
        return stock;
    }
}
