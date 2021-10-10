package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.AlarmResourceAccess;
import stockalarm.service.AlarmService;
import stockalarm.to.AlarmDTO;
import stockalarm.to.AlarmQueryFilter;
import stockalarm.to.CreateAlarmDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping
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
    public void updateAlarm(@NotNull @Valid @RequestBody final CreateAlarmDTO createAlarmDTO,
                            @PathVariable("alarmId") long alarmId) {
        alarmService.updateAlarm(createAlarmDTO, alarmId);
    }

    @DeleteMapping("/alarms/{alarmId}")
    public void deleteAlarm(@PathVariable("alarmId") long alarmId) {
        alarmService.deleteAlarm(alarmId);
    }

}