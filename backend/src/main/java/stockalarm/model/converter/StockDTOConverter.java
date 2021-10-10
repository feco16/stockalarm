package stockalarm.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.model.entity.Stock;
import stockalarm.to.StockDTO;

@Component
public class StockDTOConverter implements Converter<Stock, StockDTO> {

    @Override
    public StockDTO convert(final Stock source) {
        return StockDTO.builder()
                .stockUUID(source.getStockUUID())
                .stockName(source.getStockName())
                .symbol(source.getSymbol())
                .currentPrice(source.getCurrentPrice()).build();
    }
}
