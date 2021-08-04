package com.devm8.stockalarm.service;

import com.devm8.stockalarm.config.email.EmailFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Qualifier("gmail")
    private final JavaMailSender javaMailSender;

    @Value("${email.is.enabled}")
    private Boolean isEnabled;

    @Value("${email.from.address}")
    private String emailAddress;

    public void handleMail(final EmailFormat emailFormat) {
        if (isEnabled) {
            log.info("Sending email to {}", emailFormat.getEmail());
            sendMail(emailFormat);
        } else {
            log.info("Email service is disabled");
        }
    }

    private void sendMail(final EmailFormat emailFormat) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(emailFormat.getEmail());
            message.setFrom(emailAddress, "StockAlarm sys admin");
            message.setSubject(emailFormat.getSubject());

            message.setText(emailFormat.constructBody(), false);
        };
        javaMailSender.send(preparator);
    }

}
