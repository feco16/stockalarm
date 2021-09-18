package stockalarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceService {

    private final StockService stockService;
    private final AlarmService alarmService;

//    @Scheduled(fixedRateString = "${stock.check.interval}", initialDelay = 3000)
    public void schedulePriceCheck() {
        System.out.println("TIME: " + System.currentTimeMillis() / 1000);
        stockService.actualizePrices();
        alarmService.updateAllAlarms();
    }
}
