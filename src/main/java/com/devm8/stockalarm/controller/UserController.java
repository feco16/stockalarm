package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.service.AlarmService;
import com.devm8.stockalarm.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final StockService stockService;
    private final AlarmService alarmService;

    @GetMapping
    public String userIndex(final Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "index";
    }

}
