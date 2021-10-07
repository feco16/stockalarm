package stockalarm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stockalarm.config.security.SecurityUser;
import stockalarm.config.security.jwt.JwtUtility;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.converter.StockUserConverter;
import stockalarm.model.dto.JwtRequest;
import stockalarm.model.dto.JwtResponse;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.UserRepository;
import stockalarm.to.StockUserRegistrationDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService{

    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    private final UserRepository userRepository;
    private final MyUserDetailsService userDetailsService;
    private final StockUserConverter stockUserConverter;

    public void createUser(final StockUserRegistrationDTO stockUserRegistrationDTO) {
        final Optional<StockUser> stockUser = userRepository.findByEmail(stockUserRegistrationDTO.getEmail());
        if (stockUser.isPresent()) {
            throw new CustomBadRequestException("Email " + stockUserRegistrationDTO.getEmail()
                    + " already exists.");
        }
        if (!stockUserRegistrationDTO.getPassword().equals(stockUserRegistrationDTO.getConfirmPassword())) {
            throw new CustomBadRequestException("The given passwords are different!");
        }
        final StockUser newStockUser = stockUserConverter.convert(stockUserRegistrationDTO);
        userRepository.save(newStockUser);
    }

    public JwtResponse authenticate(final JwtRequest jwtRequest) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new CustomBadRequestException("Invalid Credentials", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

}
