package com.devm8.stockalarm.clients.email;

import org.springframework.stereotype.Component;

@Component
public class EmailService {

    public void sendEmail() {
        System.out.println("SEND EMAIL!");
    }
}
