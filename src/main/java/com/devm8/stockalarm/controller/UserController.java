package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.model.dto.AlarmDTO;
import com.devm8.stockalarm.model.dto.StockDTO;
import com.devm8.stockalarm.service.AlarmService;
import com.devm8.stockalarm.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    StockService stockService;

    @Autowired
    AlarmService alarmService;

    @GetMapping
    public String userIndex(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "user/index";
    }

    @PostMapping("/stock")
    public String createStock(@ModelAttribute("stock") StockDTO stockDTO) {
        logger.info("Create stock: {}", stockDTO);
        stockService.createStock(stockDTO);

        return "redirect:/user";
    }

    @GetMapping("/alarms")
    public String userAlarms(Model model, Principal principal) {
        model.addAttribute("alarms", alarmService.getAlarmsByUser(principal.getName()));
        model.addAttribute("alarmUpdate", new AlarmDTO());
        return "user/alarms";
    }

    @PostMapping("/alarm")
    public String createAlarm(@ModelAttribute("alarm") AlarmDTO alarmDTO) {
        logger.info("Create alarm: {}", alarmDTO);
        alarmService.createAlarm(alarmDTO);

        return "redirect:/user/alarms";
    }

    @GetMapping("/alarms/delete/{id}")
    public String deleteAlarm(@PathVariable String id) {
        logger.info("Delete alarm with id: {}", id);
        alarmService.deleteAlarm(id);

        return "redirect:/user/alarms";

    }

    @PostMapping("/alarms/update")
    public String updateAlarm(@ModelAttribute("alarmUpdate") AlarmDTO alarmDTO) {
        // TODO
        logger.info("Update alarm to: {}", alarmDTO);
        alarmService.updateAlarm(alarmDTO);

        return "redirect:/user/alarms";

    }
}
