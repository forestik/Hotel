package com.hotels.service.impl;

import com.hotels.ModelUtils;
import com.hotels.dto.EmailDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.ITemplateEngine;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private ITemplateEngine templateEngine;

    private EmailServiceImpl emailService;

    @BeforeEach
    public void setup() throws MessagingException {
        emailService = new EmailServiceImpl(javaMailSender, templateEngine,
            "http://localhost:8080", "@test@gmail.com");

        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
    }

    @Test
    void sendVerificationEmail() {
        EmailDto emailDto = ModelUtils.getEmailDto();
        emailService.sendVerificationEmail(emailDto);
        verify(javaMailSender).createMimeMessage();
    }

    @Test
    void sendInfoEmail() {
        EmailDto emailDto = ModelUtils.getEmailDto();
        emailService.sendInfoEmail(emailDto);
        verify(javaMailSender).createMimeMessage();
    }

}
