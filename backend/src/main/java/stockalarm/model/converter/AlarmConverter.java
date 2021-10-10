package stockalarm.model.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.model.entity.Alarm;
import stockalarm.model.entity.Stock;
import stockalarm.model.entity.StockUser;
import stockalarm.service.StockResourceAccessInternal;
import stockalarm.service.UserResourceAccessInternal;
import stockalarm.to.CreateAlarmDTO;

@Component
@RequiredArgsConstructor
public class AlarmConverter implements Converter<CreateAlarmDTO, Alarm> {

    private final StockResourceAccessInternal stockResourceAccessInternal;
    private final UserResourceAccessInternal userResourceAccessInternal;

    @Override
    public Alarm convert(final CreateAlarmDTO source) {
        final StockUser stockUser = userResourceAccessInternal.getById(source.getUserId());
        final Stock stock = stockResourceAccessInternal.getById(source.getStockId());
        return Alarm.builder()
                .alarmName(source.getAlarmName())
                .targetPercentage(source.getTargetPercentage())
                .stockUser(stockUser)
                .stock(stock)
                .savedPrice(stock.getCurrentPrice())
                .status(true).build();
    }

}
