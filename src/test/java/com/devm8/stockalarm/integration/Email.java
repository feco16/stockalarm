package com.devm8.stockalarm.integration;


import com.devm8.stockalarm.StockalarmApplication;
import com.devm8.stockalarm.config.email.EmailFormat;
import com.devm8.stockalarm.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = StockalarmApplication.class)
@ActiveProfiles("test")
public class Email {

    @Autowired
    EmailService emailService;

    @Test
    public void testSendEmail(){
        EmailFormat emailFormat = new EmailFormat(
                "ferenc.solyom1@gmail.com",
                "Ferenc",
                "Mega important alarm");

        emailService.handleMail(emailFormat);
    }
}
