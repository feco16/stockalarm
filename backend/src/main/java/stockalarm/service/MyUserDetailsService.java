package stockalarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import stockalarm.config.security.SecurityUser;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.UserRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<StockUser> stockUser = userRepository.findByEmail(username);
        if (stockUser.isEmpty()) {
            throw  new UsernameNotFoundException("Username " + username + " not found");
        }
        return new SecurityUser(stockUser.get(), null);    }
}
