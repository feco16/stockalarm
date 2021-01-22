package com.devm8.stockalarm.service;

import com.devm8.stockalarm.model.converter.StockUserConverter;
import com.devm8.stockalarm.model.dto.StockUserRegistrationDTO;
import com.devm8.stockalarm.model.entity.StockUser;
import com.devm8.stockalarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    StockUserConverter stockUserConverter;

    public void createUser(StockUserRegistrationDTO stockUserRegistrationDTO) {
        StockUser stockUser = stockUserConverter.convert(stockUserRegistrationDTO);
        userRepository.save(stockUser);
    }

    public StockUser getByEmail(String email) {
        StockUser stockUser = userRepository.findByEmail(email);
        if (stockUser == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return stockUser;
    }

}
