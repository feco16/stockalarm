package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StockController {

    private final StockService stockService;

    @PostMapping("/stock")
    public String createStock(@ModelAttribute("stock") final StockDTO stockDTO) {
        log.info("Create stock: {}", stockDTO);
        stockService.createStock(stockDTO);

        return "redirect:/user";
    }

}
