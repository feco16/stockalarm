package stockalarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stockalarm.Utils;
import stockalarm.config.email.EmailFormat;
import stockalarm.model.converter.AlarmConverter;
import stockalarm.model.converter.AlarmDTOConverter;
import stockalarm.model.entity.Alarm;
import stockalarm.repository.AlarmRepository;
import stockalarm.to.AlarmDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final AlarmDTOConverter alarmDTOConverter;
    private final AlarmConverter alarmConverter;
    private final EmailService emailService;

    public List<AlarmDTO> getAlarmsByUser(final String email) {
        final List<AlarmDTO> alarmDTOList = new ArrayList<>();
        alarmRepository.findAll().stream()
                .filter(a -> null != a.getStockUser() && a.getStockUser().getEmail().equals(email))
                .forEach(a -> alarmDTOList.add(alarmDTOConverter.convert(a)));
        return alarmDTOList;
    }

    public void createAlarm(final AlarmDTO alarmDTO) {
        alarmRepository.save(Objects.requireNonNull(alarmConverter.convert(alarmDTO)));
    }

    public void deleteAlarm(final String alarmUuid) {
        final Alarm alarm = alarmRepository.findByAlarmUUID(alarmUuid);
        if (null == alarm) {
            log.info("Alarm with id {} does not exists", alarmUuid);
            return;
        }
        alarmRepository.delete(alarm);
    }

    public void updateAlarm(final AlarmDTO alarmDTO) {
        final Alarm alarm = alarmRepository.findByAlarmUUID(alarmDTO.getAlarmUUID());
        if (null == alarm) {
            log.info("Alarm with id {} does not exists", alarmDTO.getAlarmUUID());
            return;
        }
        alarm.setAlarmName(alarmDTO.getAlarmName());
        alarm.setTargetPercentage(alarmDTO.getTargetPercentage());
        alarm.getStock().setSymbol(alarmDTO.getSymbol());
        alarmRepository.save(alarm);
    }

    public void updateAllAlarms() {
        alarmRepository.findAll().stream()
                .filter(Alarm::getStatus)
                .forEach(this::handleAlarm);
    }

    private void handleAlarm(final Alarm alarm) {
        final Double actualPercentage = Utils.formatDouble(calculatePercentage(alarm));
        if (Utils.compareDouble(actualPercentage, alarm.getActualPercentage())) {
            log.info("Alarm {} value not changed", alarm.getAlarmName());
            return;
        }
        alarm.setActualPercentage(actualPercentage);
        if (isAlarmTriggered(alarm, actualPercentage)) {
            alarm.setStatus(false);
            final EmailFormat emailFormat = new EmailFormat(
                    alarm.getStockUser().getEmail(),
                    alarm.getStockUser().getFirstName(),
                    alarm.getAlarmName());
            emailService.handleMail(emailFormat);
        }
        log.info("Updating alarm {}", alarm.getAlarmName());
        alarmRepository.save(alarm);
    }

    private Double calculatePercentage(final Alarm alarm) {
        if (null == alarm.getStock()) {
            return 0.;
        }
        return alarm.getStock().getCurrentPrice() * 100 / alarm.getSavedPrice() - 100;
    }

    private boolean isAlarmTriggered(final Alarm alarm, final Double actualPercentage) {
        if (null == alarm.getTargetPercentage()) {
            return false;
        }
        if (alarm.getTargetPercentage() > 0) {
            return actualPercentage > alarm.getTargetPercentage();
        }
        return actualPercentage < alarm.getTargetPercentage();
    }

}
