package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.AlarmService;
import stockalarm.to.AlarmDTO;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @PostMapping("/alarms")
    public void createAlarm(final AlarmDTO alarmDTO) {
        alarmService.createAlarm(alarmDTO);
    }

    @PutMapping("/alarms")
    public void updateAlarm(final AlarmDTO alarmDTO) {
        alarmService.updateAlarm(alarmDTO);
    }

    @DeleteMapping("/alarms")
    public void deleteAlarm(String id) {
        alarmService.deleteAlarm(id);
    }

}
