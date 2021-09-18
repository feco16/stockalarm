package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.UserService;
import stockalarm.to.StockUserRegistrationDTO;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody final StockUserRegistrationDTO user) {
        userService.createUser(user);
    }

}
