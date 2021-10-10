package stockalarm.model.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.model.entity.Stock;
import stockalarm.to.CreateStockDTO;

import java.util.UUID;

@Component
public class StockConverter implements Converter<CreateStockDTO, Stock> {

    @Override
    public Stock convert(final CreateStockDTO source) {
        final Stock stock = new Stock();
        stock.setStockUUID(UUID.randomUUID().toString());
        stock.setStockName(source.getStockName());
        stock.setSymbol(source.getSymbol());
        return stock;
    }
}
