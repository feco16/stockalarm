package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.service.user.UserService;
import stockalarm.to.StockUserRegistrationDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@RequestBody @Valid @NotNull final StockUserRegistrationDTO user) {
        userService.createUser(user);
    }

}
