package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.dto.AlarmDTO;
import com.devm8.stockalarm.dto.StockDTO;
import com.devm8.stockalarm.dto.StockUserRegistrationDTO;
import com.devm8.stockalarm.service.AlarmService;
import com.devm8.stockalarm.service.StockService;
import com.devm8.stockalarm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    UserService userService;

    @Autowired
    StockService stockService;

    @Autowired
    AlarmService alarmService;

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrate(@ModelAttribute("user") StockUserRegistrationDTO stockUserRegistrationDTO) {
        return "registration";
    }

    @PostMapping("/registration")
    String registrationPost(@ModelAttribute("user") StockUserRegistrationDTO stockUserRegistrationDTO) {

        logger.info("Create user: {}", stockUserRegistrationDTO);
        userService.createUser(stockUserRegistrationDTO);

        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userIndex(Model model) {
        model.addAttribute("stocks", stockService.getAllStocks());
        return "user/index";
    }

    @PostMapping("/user/stock")
    public String createStock(@ModelAttribute("stock") StockDTO stockDTO) {
        logger.info("Create stock: {}", stockDTO);
        stockService.createStock(stockDTO);

        return "redirect:/user";
    }

    @GetMapping("/user/alarms")
    public String userAlarms(Model model) {
        model.addAttribute("alarms", alarmService.getAllStocks());
        return "user/alarms";
    }

    @PostMapping("/user/alarm")
    public String createAlarm(@ModelAttribute("alarm") AlarmDTO alarmDTO) {
        logger.info("Create alarm: {}", alarmDTO);
        alarmService.createAlarm(alarmDTO);

        return "redirect:/user/alarms";
    }
}