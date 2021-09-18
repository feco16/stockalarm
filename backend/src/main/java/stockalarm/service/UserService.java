package stockalarm.service;

import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import stockalarm.exception.CustomBadRequestException;
import stockalarm.model.converter.StockUserConverter;
import stockalarm.model.entity.StockUser;
import stockalarm.repository.UserRepository;
import stockalarm.to.StockUserRegistrationDTO;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StockUserConverter stockUserConverter;

    public void createUser(final StockUserRegistrationDTO stockUserRegistrationDTO) {
        final StockUser stockUser = userRepository.findByEmail(stockUserRegistrationDTO.getEmail());
        if (stockUser != null) {
            throw new CustomBadRequestException("Email " + stockUserRegistrationDTO.getEmail()
            + " already exists.");
        }
        if (!stockUserRegistrationDTO.getPassword().equals(stockUserRegistrationDTO.getConfirmPassword())) {
            throw new CustomBadRequestException("The given passwords are different!");
        }
        final StockUser newStockUser = stockUserConverter.convert(stockUserRegistrationDTO);
        userRepository.save(newStockUser);
    }

    public StockUser getByEmail(final String email) {
        final StockUser stockUser = userRepository.findByEmail(email);
//        if (stockUser == null) {
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
        return stockUser;
    }

}
