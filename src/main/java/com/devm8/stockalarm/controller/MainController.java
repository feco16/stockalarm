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

import java.security.Principal;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    UserService userService;

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


}