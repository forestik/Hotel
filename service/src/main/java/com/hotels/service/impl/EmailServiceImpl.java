package com.hotels.service.impl;

import com.hotels.constant.EmailConstants;
import com.hotels.constant.LogMessage;
import com.hotels.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;
    private final String clientLink;
    private final String senderEmailAddress;

    /**
     * Constructor.
     */
    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            ITemplateEngine templateEngine,
                            @Value("${client.address}") String clientLink,
                            @Value("${sender.email.address}") String senderEmailAddress) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.clientLink = clientLink;
        this.senderEmailAddress = senderEmailAddress;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void sendVerificationEmail(Long id, String name, String email, String token) {
        Map<String, Object> model = new HashMap<>();
        model.put(EmailConstants.CLIENT_LINK, clientLink);
        model.put(EmailConstants.USER_NAME, name);
        model.put(EmailConstants.VERIFY_ADDRESS, clientLink + "/verifyEmail?token="
            + token + "&user_id=" + id);
        log.info(Locale.getDefault().toString());
        String template = createEmailTemplate(model, EmailConstants.VERIFY_EMAIL_PAGE);
        sendEmail(email, EmailConstants.VERIFY_EMAIL, template);
    }

    private String createEmailTemplate(Map<String, Object> vars, String templateName) {
        log.info(LogMessage.IN_CREATE_TEMPLATE_NAME, null, templateName);
        Context context = new Context();
        context.setVariables(vars);
        return templateEngine.process("email/" + templateName, context);
    }

    private void sendEmail(String receiverEmail, String subject, String content) {
        log.info(LogMessage.IN_SEND_EMAIL, receiverEmail, subject);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(senderEmailAddress);
            mimeMessageHelper.setTo(receiverEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessage.setContent(content, EmailConstants.EMAIL_CONTENT_TYPE);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
        javaMailSender.send(mimeMessage);
    }

}
