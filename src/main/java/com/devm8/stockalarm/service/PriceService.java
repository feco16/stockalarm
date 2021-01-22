package com.devm8.stockalarm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PriceService {

    @Autowired
    private StockService stockService;

    @Autowired
    private AlarmService alarmService;

    @Scheduled(fixedRateString = "${stock.check.interval}", initialDelay = 3000)
    public void schedulePriceCheck() {
        System.out.println("TIME: " + System.currentTimeMillis() / 1000);
        stockService.actualizePrices();
        alarmService.updateAllAlarms();
    }
}
