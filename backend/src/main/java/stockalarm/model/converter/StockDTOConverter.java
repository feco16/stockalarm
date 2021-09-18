package stockalarm.model.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.Utils;
import stockalarm.clients.stockapi.ClientStrategyFactory;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.entity.Stock;
import stockalarm.to.StockDTO;

@Component
@Slf4j
public class StockDTOConverter implements Converter<Stock, StockDTO> {

    @Autowired
    private ClientStrategyFactory clientStrategyFactory;

    @Override
    public StockDTO convert(final Stock source) {
        final StockDTO stockDTO = new StockDTO();
        stockDTO.setStockUUID(source.getStockUUID());
        stockDTO.setStockName(source.getStockName());
        stockDTO.setSymbol(source.getSymbol());

        final String price = "NaN";
        try {
        //  price = clientStrategyFactory.findStrategy(ClientEnum.ALPHAVANTAGE).getPrice(source.getSymbol());
        } catch (CustomBadRequestException e) {
            log.warn("Can't fetch the current price for {}", source.getSymbol());
        }

        stockDTO.setCurrentPrice(Utils.formatDouble(source.getCurrentPrice()));
        return stockDTO;
    }
}
