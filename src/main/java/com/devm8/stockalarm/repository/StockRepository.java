package com.devm8.stockalarm.repository;

import com.devm8.stockalarm.model.Stock;
import org.springframework.data.repository.CrudRepository;

public interface StockRepository extends CrudRepository<Stock, Long> {
}
