package com.devm8.stockalarm.converter;

import com.devm8.stockalarm.dto.StockUserRegistrationDTO;
import com.devm8.stockalarm.model.StockUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StockUserConverter {

    public StockUser convert(StockUserRegistrationDTO source) {
        StockUser stockUser = new StockUser();
        stockUser.setUserUUID(UUID.randomUUID().toString());
        stockUser.setFirstName(source.getFirstName());
        stockUser.setLastName(source.getLastName());
        stockUser.setEmail(source.getEmail());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        stockUser.setPassword(bCryptPasswordEncoder.encode(source.getPassword()));

//        stockUser.setPassword(source.getPassword());
        return stockUser;
    }
}
