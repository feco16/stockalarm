package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import stockalarm.StockClient;
import stockalarm.to.StockDTO;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockClient stockClient;

    @PostMapping("/stock")
    public String createStock(@ModelAttribute("stock") final StockDTO stockDTO) {
        log.info("Create stock: {}", stockDTO);
        stockClient.createStock(stockDTO);

        return "redirect:/index";
    }

}
