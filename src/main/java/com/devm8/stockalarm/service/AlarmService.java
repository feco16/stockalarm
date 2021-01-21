package com.devm8.stockalarm.service;

import com.devm8.stockalarm.converter.AlarmConverter;
import com.devm8.stockalarm.converter.AlarmDTOConverter;
import com.devm8.stockalarm.dto.AlarmDTO;
import com.devm8.stockalarm.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlarmService {

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private AlarmDTOConverter alarmDTOConverter;

    @Autowired
    private AlarmConverter alarmConverter;

    public List<AlarmDTO> getAllStocks() {
        List<AlarmDTO> alarmDTOList = new ArrayList<>();
        alarmRepository.findAll().forEach(
                s -> alarmDTOList.add(alarmDTOConverter.convert(s))
        );
        return alarmDTOList;
    }

    public void createAlarm(AlarmDTO alarmDTO) {
        alarmRepository.save(alarmConverter.convert(alarmDTO));
    }

}
