package stockalarm.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.entity.Alarm;
import stockalarm.model.entity.Stock;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.StockRepository;
import stockalarm.repository.UserRepository;
import stockalarm.to.AlarmDTO;

import java.util.UUID;

@Component
public class AlarmConverter implements Converter<AlarmDTO, Alarm> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public Alarm convert(final AlarmDTO source) {
        final Alarm alarm = new Alarm();
        alarm.setAlarmUUID(UUID.randomUUID().toString());
        alarm.setAlarmName(source.getAlarmName());
        alarm.setTargetPercentage(source.getTargetPercentage());

//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        final StockUser stockUser = userRepository.findByEmail(authentication.getName());
//        if (null != stockUser) {
//            alarm.setStockUser(stockUser);
//        }

        final Stock stock = stockRepository.findBySymbol(source.getSymbol());
        if (null != stock) {
            alarm.setStock(stock);
            alarm.setSavedPrice(stock.getCurrentPrice());
        } else {
            throw new CustomBadRequestException("Can't create alarm for stock symbol " + source.getSymbol()
                    + " . Check available stocks");
        }
        alarm.setStatus(true);
        return alarm;
    }
}
