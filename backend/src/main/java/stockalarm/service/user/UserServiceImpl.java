package stockalarm.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import stockalarm.model.converter.StockUserConverter;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.UserRepository;
import stockalarm.service.KeycloakAdminClientService;
import stockalarm.to.StockUserRegistrationDTO;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final KeycloakAdminClientService keycloakAdminClientService;
    private final StockUserConverter stockUserConverter;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createUser(final StockUserRegistrationDTO userRegistrationDTO) {
        final int statusCode = keycloakAdminClientService.addUser(userRegistrationDTO);
        if (statusCode == HttpStatus.CONFLICT.value()) {
            log.error("User already exists in keycloak. Error code {}", statusCode);
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Keycloak error");
        }
        if (statusCode != HttpStatus.CREATED.value()) {
            log.error("Cannot create keycloak user. Error code {}", statusCode);
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Keycloak error");
        }
        final StockUser stockUser = stockUserConverter.convert(userRegistrationDTO);
        userRepository.save(stockUser);
    }
}
