package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.alarm.AlarmResourceAccess;
import stockalarm.service.alarm.AlarmService;
import stockalarm.to.AlarmDTO;
import stockalarm.to.AlarmQueryFilter;
import stockalarm.to.CreateAlarmDTO;
import stockalarm.to.UpdateAlarmDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmResourceAccess alarmResourceAccess;
    private final AlarmService alarmService;

    @PostMapping("/alarms")
    public void createAlarm(@NotNull @Valid @RequestBody final CreateAlarmDTO alarmDTO) {
        alarmService.createAlarm(alarmDTO);
    }

    @PostMapping("/alarms/filter")
    public List<AlarmDTO> getAlarms(@NotNull @Valid @RequestBody final AlarmQueryFilter alarmQueryFilter) {
        return alarmResourceAccess.getAlarms(alarmQueryFilter);
    }

    @PutMapping("/alarms/{alarmId}")
    public void updateAlarm(@NotNull @Valid @RequestBody final UpdateAlarmDTO updateAlarmDTO,
                            @PathVariable("alarmId") long alarmId) {
        alarmService.updateAlarm(updateAlarmDTO, alarmId);
    }

    @DeleteMapping("/alarms/{alarmId}")
    public void deleteAlarm(@PathVariable("alarmId") long alarmId) {
        alarmService.deleteAlarm(alarmId);
    }

}
