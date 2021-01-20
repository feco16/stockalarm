package com.devm8.stockalarm.unit;

import com.devm8.stockalarm.StockalarmApplication;
import com.devm8.stockalarm.service.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = StockalarmApplication.class)
@ActiveProfiles("test")
public class TestStockService {

    @Autowired
    public StockService stockService;

    @Test
    public void getAllStocks() {

        stockService.getAllStocks();
    }
}
