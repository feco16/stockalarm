package com.devm8.stockalarm.model.converter;

import com.devm8.stockalarm.exception.CustomBadRequestException;
import com.devm8.stockalarm.model.dto.AlarmDTO;
import com.devm8.stockalarm.model.entity.Alarm;
import com.devm8.stockalarm.model.entity.Stock;
import com.devm8.stockalarm.model.entity.StockUser;
import com.devm8.stockalarm.repository.StockRepository;
import com.devm8.stockalarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlarmConverter implements Converter<AlarmDTO, Alarm> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public Alarm convert(AlarmDTO source) {
        Alarm alarm = new Alarm();
        alarm.setAlarmUUID(UUID.randomUUID().toString());
        alarm.setAlarmName(source.getAlarmName());
        alarm.setTargetPercentage(source.getTargetPercentage());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StockUser stockUser = userRepository.findByEmail(authentication.getName());
        if (null != stockUser) {
            alarm.setStockUser(stockUser);
        }

        Stock stock = stockRepository.findBySymbol(source.getSymbol());
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
