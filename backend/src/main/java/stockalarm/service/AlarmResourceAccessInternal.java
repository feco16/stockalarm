package stockalarm.service;

import stockalarm.model.entity.Alarm;

public interface AlarmResourceAccessInternal extends AlarmResourceAccess {

    Alarm getById(long id);
}
