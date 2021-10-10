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
        final Double savedPrice = Utils.formatDouble(source.getSavedPrice());
        final AlarmDTO alarmDTO = AlarmDTO.builder()
                .alarmName(source.getAlarmName())
                .targetPercentage(source.getTargetPercentage())
                .actualPercentage(source.getActualPercentage() != null ? source.getActualPercentage() : 0)
                .status(source.getStatus())
                .savedPrice(savedPrice)
                .symbol(source.getStock().getSymbol()).build();
        if (null != source.getStock()) {
            alarmDTO.setSymbol(source.getStock().getSymbol());
            alarmDTO.setCurrentPrice(Utils.formatDouble(source.getStock().getCurrentPrice()));
        }
        return alarmDTO;
    }
}
