package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.model.dto.AlarmDTO;
import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.service.AlarmService;
import com.devm8.stockalarm.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
        return "user/index";
    }

    @PostMapping("/stock")
    public String createStock(@ModelAttribute("stock") final StockDTO stockDTO) {
        log.info("Create stock: {}", stockDTO);
        stockService.createStock(stockDTO);

        return "redirect:/user";
    }

    @GetMapping("/alarms")
    public String userAlarms(final Model model, final Principal principal) {
        model.addAttribute("alarms", alarmService.getAlarmsByUser(principal.getName()));
        model.addAttribute("alarmUpdate", new AlarmDTO());
        return "user/alarms";
    }

    @PostMapping("/alarm")
    public String createAlarm(@ModelAttribute("alarm") final AlarmDTO alarmDTO) {
        log.info("Create alarm: {}", alarmDTO);
        alarmService.createAlarm(alarmDTO);

        return "redirect:/user/alarms";
    }

    @GetMapping("/alarms/delete/{id}")
    public String deleteAlarm(@PathVariable final String id) {
        log.info("Delete alarm with id: {}", id);
        alarmService.deleteAlarm(id);

        return "redirect:/user/alarms";

    }

    @PostMapping("/alarms/update")
    public String updateAlarm(@ModelAttribute("alarmUpdate") final AlarmDTO alarmDTO) {
        // TODO
        log.info("Update alarm to: {}", alarmDTO);
        alarmService.updateAlarm(alarmDTO);

        return "redirect:/user/alarms";

    }
}
