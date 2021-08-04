package com.devm8.stockalarm.service;

import com.devm8.stockalarm.exception.CustomBadRequestException;
import com.devm8.stockalarm.model.converter.StockUserConverter;
import com.devm8.stockalarm.model.dto.StockUserRegistrationDTO;
import com.devm8.stockalarm.model.entity.StockUser;
import com.devm8.stockalarm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        if (stockUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return stockUser;
    }

}
