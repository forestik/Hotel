package com.hotels.service.impl;

import com.hotels.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.ITemplateEngine;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private ITemplateEngine iTemplateEngine;

    @InjectMocks
    private EmailServiceImpl emailService;

    private String clientLink = "http://test/";

    private String senderEmailAddress = "test@gmail.com";

    @Test
    void sendVerificationEmail() {
    }

    @Test
    void sendEmail() {
    }
}
