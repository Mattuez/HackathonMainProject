package com.matheus.hackathonproject.infraestructure.email;

import com.matheus.hackathonproject.core.email.EmailProperties;
import com.matheus.hackathonproject.domain.exceptions.EmailException;
import com.matheus.hackathonproject.domain.service.EmailSenderService;
import freemarker.template.Configuration;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Slf4j
public class SandBoxEmailSenderService extends SmtpEmailSenderService implements EmailSenderService {
    public SandBoxEmailSenderService(JavaMailSender mailSender, EmailProperties emailProperties, Configuration configuration) {
        super(mailSender, emailProperties, configuration);
    }

    @Override
    public void send(Message message) {
        MimeMessage mimeMessage = getMimeMessage(message);

        mailSender.send(mimeMessage);
    }

    protected MimeMessage getMimeMessage(Message message) {
        try {
            MimeMessage mimeMessage = super.getMimeMessage(message);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setTo("matheusmedeirosprogramacao@gmail.com");

            return mimeMessage;
        } catch (Exception e) {
            throw new EmailException("It was not possible to send email", e);
        }
    }
}
