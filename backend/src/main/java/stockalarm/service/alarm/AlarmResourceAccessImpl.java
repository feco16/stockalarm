package stockalarm.service.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.converter.AlarmDTOConverter;
import stockalarm.model.entity.Alarm;
import stockalarm.repository.AlarmRepository;
import stockalarm.to.AlarmDTO;
import stockalarm.to.AlarmQueryFilter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlarmResourceAccessImpl implements AlarmResourceAccessInternal {

    private final AlarmRepository alarmRepository;
    private final AlarmDTOConverter alarmDTOConverter;

    @Override
    public List<AlarmDTO> getAlarms(AlarmQueryFilter alarmQueryFilter) {
        //TODO filter by alarmQueryFilter
        final List<Alarm> alarms = alarmRepository.findAll();
        return alarms.stream()
                .map(alarmDTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public Alarm getById(long id) {
        final Optional<Alarm> alarmOptional = alarmRepository.findById(id);
        return alarmOptional.orElseThrow(() -> new CustomBadRequestException("Can't find alarm with id " + id));
    }
}
