package com.devm8.stockalarm.service;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.config.email.EmailFormat;
import com.devm8.stockalarm.model.converter.AlarmConverter;
import com.devm8.stockalarm.model.converter.AlarmDTOConverter;
import com.devm8.stockalarm.model.dto.AlarmDTO;
import com.devm8.stockalarm.model.entity.Alarm;
import com.devm8.stockalarm.repository.AlarmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmService {

    private static final Logger logger = LoggerFactory.getLogger(AlarmService.class);

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private AlarmDTOConverter alarmDTOConverter;

    @Autowired
    private AlarmConverter alarmConverter;

    @Autowired
    private EmailService emailService;

    public List<AlarmDTO> getAlarmsByUser(String email) {
        List<AlarmDTO> alarmDTOList = new ArrayList<>();
        alarmRepository.findAll().stream()
                .filter(a -> {
                    if (null != a.getStockUser()) {
                        return a.getStockUser().getEmail().equals(email);
                    }
                    return false;
                })
                .forEach(
                        a -> alarmDTOList.add(alarmDTOConverter.convert(a))
                );
        return alarmDTOList;
    }

    public void createAlarm(AlarmDTO alarmDTO) {
        alarmRepository.save(alarmConverter.convert(alarmDTO));
    }

    public void deleteAlarm(String alarmUuid) {
        Alarm alarm = alarmRepository.findByAlarmUUID(alarmUuid);
        if (null == alarm) {
            logger.info("Alarm with id {} does not exists", alarmUuid);
            return;
        }
        alarmRepository.delete(alarm);
    }

    public void updateAlarm(AlarmDTO alarmDTO) {
        Alarm alarm = alarmRepository.findByAlarmUUID(alarmDTO.getAlarmUUID());
        if (null == alarm) {
            logger.info("Alarm with id {} does not exists", alarmDTO.getAlarmUUID());
            return;
        }
        alarm.setAlarmName(alarmDTO.getAlarmName());
        alarm.setTargetPercentage(alarmDTO.getTargetPercentage());
        alarm.getStock().setSymbol(alarmDTO.getSymbol());
        alarmRepository.save(alarm);
    }

    public void updateAllAlarms() {
        alarmRepository.findAll().stream()
                .filter(a -> a.getStatus() == true)
                .forEach(a -> handleAlarm(a));
    }

    private void handleAlarm(Alarm alarm) {
        Double actualPercentage = Utils.formatDouble(calculateAlarm(alarm));
        if (Utils.compareDouble(actualPercentage, alarm.getActualPercentage())) {
            logger.info("Alarm {} value not changed", alarm.getAlarmName());
            return;
        }
        alarm.setActualPercentage(actualPercentage);
        if (isAlarmTriggered(alarm, actualPercentage)) {
            alarm.setStatus(false);
            EmailFormat emailFormat = new EmailFormat(
                    alarm.getStockUser().getEmail(),
                    alarm.getStockUser().getFirstName(),
                    alarm.getAlarmName());
            emailService.handleMail(emailFormat);
        }
        logger.info("Updating alarm {}", alarm.getAlarmName());
        alarmRepository.save(alarm);
    }

    private Double calculateAlarm(Alarm alarm) {
        if (null == alarm.getStock()) {
            return 0.;
        }
        Double actualPercentage = 100 - alarm.getStock().getCurrentPrice() * 100 / alarm.getSavedPrice();
        return actualPercentage;
    }

    private boolean isAlarmTriggered(Alarm alarm, Double actualPercentage) {
        if (null == alarm.getTargetPercentage()) {
            return false;
        }
        if (alarm.getTargetPercentage() > 0) {
            return actualPercentage < alarm.getTargetPercentage();
        }
        return actualPercentage > alarm.getTargetPercentage();
    }

}
