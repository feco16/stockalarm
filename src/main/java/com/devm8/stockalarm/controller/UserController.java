package com.devm8.stockalarm.controller;

import com.devm8.stockalarm.dto.StockUserRegistrationDTO;
import com.devm8.stockalarm.model.StockUser;
import com.devm8.stockalarm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @ModelAttribute("stockUserRegistrationDTO")
    public StockUserRegistrationDTO stockUserRegistrationDTO() {
        return new StockUserRegistrationDTO();
    }


    @GetMapping("/signup")
    public String showSignUpForm(StockUser stockUser) {
        return "add-user";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("stockUserRegistrationDTO", new StockUserRegistrationDTO());
        return "registration";
    }

    
    @PostMapping("/registration")
    public String registerUser(@Valid StockUser stockUser, BindingResult result, Model model) {
        logger.info("Create new user");
        logger.debug("stockUser");

        if (result.hasErrors()) {
            return "registration";
        }

        userRepository.save(stockUser);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/edit/{stockUserId}")
    public String showUpdateForm(@PathVariable("stockUserId") long stockUserId, Model model) {
        StockUser stockUser = userRepository.findById(stockUserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + stockUserId));

        model.addAttribute("user", stockUser);
        return "update-user";
    }

    @PostMapping("/update/{stockUserId}")
    public String updateUser(@PathVariable("stockUserId") long stockUserId, @Valid StockUser stockUser,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            stockUser.setUserId(stockUserId);
            return "update-user";
        }

        userRepository.save(stockUser);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long stockUserId, Model model) {
        StockUser stockUser = userRepository.findById(stockUserId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + stockUserId));
        userRepository.delete(stockUser);
        return "redirect:/index";
    }

    // additional CRUD methods
}