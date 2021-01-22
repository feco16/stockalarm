package com.devm8.stockalarm.service;

import com.devm8.stockalarm.config.email.EmailFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    @Qualifier("gmail")
    private JavaMailSender javaMailSender;

    @Value("${email.is.enabled}")
    private Boolean isEnabled;

    @Value("${email.from.address}")
    private String emailAddress;

    public void handleMail(EmailFormat emailFormat) {
        if (isEnabled) {
            logger.info("Sending email to {}", emailFormat.getEmail());
            sendMail(emailFormat);
        } else {
            logger.info("Email service is disabled");
        }
    }

    private void sendMail(EmailFormat emailFormat) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(emailFormat.getEmail());
            message.setFrom(emailAddress, "StockAlarm sys admin");
            message.setSubject(emailFormat.getSubject());

            message.setText(emailFormat.constructBody(), false);
        };
        javaMailSender.send(preparator);
    }

}