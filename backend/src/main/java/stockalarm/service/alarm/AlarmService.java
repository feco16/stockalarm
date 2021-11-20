package stockalarm.service.alarm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stockalarm.service.EmailService;
import stockalarm.service.stock.StockResourceAccessInternal;
import stockalarm.utilities.Constants;
import stockalarm.utilities.Utils;
import stockalarm.config.email.EmailFormat;
import stockalarm.model.converter.AlarmConverter;
import stockalarm.model.entity.Alarm;
import stockalarm.model.entity.Stock;
import stockalarm.repository.AlarmRepository;
import stockalarm.to.CreateAlarmDTO;
import stockalarm.to.UpdateAlarmDTO;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final AlarmConverter alarmConverter;
    private final AlarmResourceAccessInternal alarmResourceAccess;
    private final EmailService emailService;
    private final StockResourceAccessInternal stockResourceAccessInternal;

    public void createAlarm(final CreateAlarmDTO alarmDTO) {
        final Alarm alarm = alarmConverter.convert(alarmDTO);
        alarmRepository.save(alarm);
    }

    public void updateAlarm(final UpdateAlarmDTO updateAlarmDTO, final long alarmId) {
        final Alarm alarm = alarmResourceAccess.getById(alarmId);
        final Alarm updatedAlarm = buildAlarm(alarm, updateAlarmDTO);
        alarmRepository.save(updatedAlarm);
    }

    public void deleteAlarm(final long id) {
        final Alarm alarm = alarmResourceAccess.getById(id);
        alarmRepository.delete(alarm);
    }

    public void updateAllAlarms() {
        alarmRepository.findAll().stream()
                .filter(Alarm::getStatus)
                .forEach(this::handleAlarm);
    }

    private Alarm buildAlarm(final Alarm alarm, final UpdateAlarmDTO updateAlarmDTO) {
        if (updateAlarmDTO.getStockId() != null) {
            final Stock stock = stockResourceAccessInternal.getById(updateAlarmDTO.getStockId());
            alarm.setStock(stock);
        }
        alarm.setAlarmName(updateAlarmDTO.getAlarmName());
        alarm.setTargetPercentage(updateAlarmDTO.getTargetPercentage());
        return alarm;
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
        if (alarm.getStock() == null || alarm.getStock().getCurrentPrice() == null || alarm.getSavedPrice() == null) {
            return Constants.DOUBLE_ZERO;
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
