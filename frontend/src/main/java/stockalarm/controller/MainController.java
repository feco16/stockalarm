package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import stockalarm.UserClient;
import stockalarm.to.StockUserRegistrationDTO;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final UserClient userClient;

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("logout")
    public String logout() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrate(@ModelAttribute("user") final StockUserRegistrationDTO stockUserRegistrationDTO) {
        return "registration";
    }

    @PostMapping("/registration")
    String registrationPost(@ModelAttribute("user") final StockUserRegistrationDTO stockUserRegistrationDTO) {

        log.info("Create user: {}", stockUserRegistrationDTO);
        userClient.createUser(stockUserRegistrationDTO);

        return "redirect:/login";
    }

}
