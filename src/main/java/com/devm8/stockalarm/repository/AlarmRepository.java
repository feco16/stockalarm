package com.devm8.stockalarm.repository;

import com.devm8.stockalarm.model.entity.Alarm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long> {

    @Override
    List<Alarm> findAll();

    Alarm findByAlarmUUID(String Uuid);
}
