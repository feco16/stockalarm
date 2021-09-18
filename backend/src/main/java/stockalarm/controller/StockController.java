package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.StockService;
import stockalarm.to.StockDTO;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/stocks")
    public void createStock(@RequestBody final StockDTO stockDTO) {
        stockService.createStock(stockDTO);
    }

    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks() {
        return stockService.getAllStocks();
    }

}
