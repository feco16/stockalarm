package stockalarm.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import stockalarm.Utils;
import stockalarm.model.entity.Alarm;
import stockalarm.to.AlarmDTO;

@Component
public class AlarmDTOConverter implements Converter<Alarm, AlarmDTO> {

    @Override
    public AlarmDTO convert(final Alarm source) {
        final AlarmDTO alarmDTO = new AlarmDTO();
        alarmDTO.setAlarmUUID(source.getAlarmUUID());
        alarmDTO.setAlarmName(source.getAlarmName());
        alarmDTO.setTargetPercentage(source.getTargetPercentage());
        alarmDTO.setActualPercentage(source.getActualPercentage() != null ? source.getActualPercentage() : 0.);
        alarmDTO.setStatus(source.getStatus());
        alarmDTO.setSavedPrice(Utils.formatDouble(source.getSavedPrice()));

        if (null != source.getStock()) {
            alarmDTO.setSymbol(source.getStock().getSymbol());
            alarmDTO.setCurrentPrice(Utils.formatDouble(source.getStock().getCurrentPrice()));
        }

        return alarmDTO;
    }
}
