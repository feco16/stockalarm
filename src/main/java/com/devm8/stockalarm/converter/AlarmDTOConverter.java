package com.devm8.stockalarm.converter;

import com.devm8.stockalarm.dto.AlarmDTO;
import com.devm8.stockalarm.model.Alarm;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AlarmDTOConverter implements Converter<Alarm, AlarmDTO> {

    @Override
    public AlarmDTO convert(Alarm source) {
        AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmName(source.getAlarmName());
        alarmDTO.setCurrentPrice(source.getCurrentPrice());
        alarmDTO.setPercentage(source.getPercentage());
        alarmDTO.setStatus(source.isStatus());

        if (null != source.getStock()) {
            alarmDTO.setSymbol(source.getStock().getSymbol());
        }

        return alarmDTO;
    }
}
