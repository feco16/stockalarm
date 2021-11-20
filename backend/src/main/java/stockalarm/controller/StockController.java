package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.stock.StockResourceAccess;
import stockalarm.service.stock.StockService;
import stockalarm.to.CreateStockDTO;
import stockalarm.to.StockDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class StockController {

    private final StockResourceAccess stockResourceAccess;
    private final StockService stockService;

    @PostMapping("/stocks")
    public void createStock(@NotNull @Valid @RequestBody final CreateStockDTO stockDTO) {
        stockService.createStock(stockDTO);
    }

    @GetMapping("/stocks")
    public List<StockDTO> getAllStocks() {
        return stockResourceAccess.getAllStocks();
    }

}
