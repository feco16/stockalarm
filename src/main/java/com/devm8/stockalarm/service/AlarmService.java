package com.devm8.stockalarm.service;

import com.devm8.stockalarm.Utils;
import com.devm8.stockalarm.clients.email.EmailService;
import com.devm8.stockalarm.converter.AlarmConverter;
import com.devm8.stockalarm.converter.AlarmDTOConverter;
import com.devm8.stockalarm.dto.AlarmDTO;
import com.devm8.stockalarm.model.Alarm;
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

    public void manageAlarms() {
        alarmRepository.findAll().stream()
                .filter(a -> a.getStatus() == true)
                .forEach(
                        a -> {
                            Double actualPercentage = Utils.formatDouble(calculateAlarm(a));
                            if (Utils.compareDouble(actualPercentage, a.getActualPercentage())) {
                                logger.info("Alarm {} value not changed", a.getAlarmName());
                                return;
                            }
                            a.setActualPercentage(actualPercentage);
                            if (isAlarmTriggered(a, actualPercentage)) {
                                a.setStatus(false);
                                emailService.sendEmail();
                            }
                            logger.info("Updating alarm {}", a.getAlarmName());
                            alarmRepository.save(a);
                        }
                );
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
