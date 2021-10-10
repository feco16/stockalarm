package stockalarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserResourceAccessImpl implements UserResourceAccessInternal{

    private final UserRepository userRepository;

    @Override
    public StockUser getById(long id) {
//        isCurrentUser(id);
        final Optional<StockUser> stockUserOptional = userRepository.findById(id);
        return stockUserOptional.orElseThrow(() -> new CustomBadRequestException("Cannot find user with id: " + id));
    }

    private void isCurrentUser(long id) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    }
}
