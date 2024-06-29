package com.matheus.hackathonproject.core.email;

import com.matheus.hackathonproject.domain.service.EmailSenderService;
import com.matheus.hackathonproject.infraestructure.email.SandBoxEmailSenderService;
import com.matheus.hackathonproject.infraestructure.email.SmtpEmailSenderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailConfig {

    private freemarker.template.Configuration configuration;
    private JavaMailSender mailSender;
    private EmailProperties emailProperties;

    public EmailConfig(freemarker.template.Configuration configuration, JavaMailSender mailSender, EmailProperties emailProperties) {
        this.configuration = configuration;
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
    }

    @Bean
    public EmailSenderService emailSenderService() {
        switch (emailProperties.getImpl()) {
            case SMTP -> {
                return new SmtpEmailSenderService(mailSender, emailProperties, configuration);
            } case SAND_BOX -> {
                return new SandBoxEmailSenderService(mailSender, emailProperties, configuration);
            } default -> {
                return null;
            }
        }
    }
}
