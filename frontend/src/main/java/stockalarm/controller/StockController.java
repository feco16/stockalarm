package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import stockalarm.StockClient;
import stockalarm.to.StockDTO;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockClient stockClient;

    @PostMapping("/stocks")
    public String createStock(@ModelAttribute("stock") final StockDTO stockDTO) {
        log.info("Create stock: {}", stockDTO);
        stockClient.createStock(stockDTO);

        return "redirect:/user/stocks";
    }

    @GetMapping("/stocks")
    public String getAllStocks(final Model model) {
        model.addAttribute("stocks", stockClient.getAllStocks());
        return "user/stocks";
    }

}
