package stockalarm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stockalarm.config.keycloak.KeycloakAdminClientService;
import stockalarm.to.StockUserRegistrationDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class UserController {

    private final KeycloakAdminClientService keycloakAdminClientService;

    @PostMapping("/users")
    public String createUser(@RequestBody @Valid @NotNull final StockUserRegistrationDTO user) {
        return keycloakAdminClientService.addUser(user).getStatusInfo().toString();
    }

}
