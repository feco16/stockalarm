package com.devm8.stockalarm.repository;

import com.devm8.stockalarm.model.Alarm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends CrudRepository<Alarm, Long> {
}
