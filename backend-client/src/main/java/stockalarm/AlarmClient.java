package stockalarm;

import org.springframework.stereotype.Component;
import stockalarm.to.AlarmDTO;

import java.util.List;

@Component
public class AlarmClient {

    public void createAlarm(final AlarmDTO alarmDTO) {

    }

    public List<AlarmDTO> getAlarmsByUser(String username) {
        return List.of();
    }

    public void updateAlarm(final AlarmDTO alarmDTO) {

    }

    public void deleteAlarm(final String id) {

    }
}
