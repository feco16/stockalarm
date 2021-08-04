package com.devm8.stockalarm.model.dto;

import lombok.Data;

@Data
public class StockUserRegistrationDTO {

    private String userUUID;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private String confirmPassword;

}
