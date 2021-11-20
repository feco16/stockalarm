package stockalarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import stockalarm.service.alarm.AlarmService;
import stockalarm.service.stock.StockService;

import java.time.Instant;

@Slf4j
@Component
@RequiredArgsConstructor
public class PriceService {

    private final StockService stockService;
    private final AlarmService alarmService;

    @Scheduled(fixedRateString = "${stockalarm.stock.check.interval}", initialDelay = 300000)
    public void schedulePriceCheck() {
        log.info("Updating prices and alarms at {}", Instant.now());
        stockService.actualizePrices();
        alarmService.updateAllAlarms();
    }
}
