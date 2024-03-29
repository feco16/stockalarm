package stockalarm.service.alarm;

import stockalarm.to.AlarmDTO;
import stockalarm.to.AlarmQueryFilter;

import java.util.List;

public interface AlarmResourceAccess {

    List<AlarmDTO> getAlarms(AlarmQueryFilter alarmQueryFilter);
}
