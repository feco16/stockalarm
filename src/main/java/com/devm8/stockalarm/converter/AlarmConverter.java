package com.devm8.stockalarm.converter;

import com.devm8.stockalarm.dto.AlarmDTO;
import com.devm8.stockalarm.model.Alarm;
import com.devm8.stockalarm.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AlarmConverter implements Converter<AlarmDTO, Alarm> {

    @Autowired
    StockRepository stockRepository;

    @Override
    public Alarm convert(AlarmDTO source) {
        Alarm alarm = new Alarm();
        alarm.setAlarmUUID(UUID.randomUUID().toString());
        alarm.setAlarmName(source.getAlarmName());
        alarm.setPercentage(source.getPercentage());
        alarm.setStock(stockRepository.findBySymbol(source.getSymbol()));
        alarm.setStatus(true);
        return alarm;
    }
}
