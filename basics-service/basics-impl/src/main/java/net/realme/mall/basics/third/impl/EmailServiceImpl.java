package net.realme.mall.basics.third.impl;

import net.realme.mall.basics.third.facade.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.third.impl
 *
 * @author 91000044
 * @date 2018/8/4 17:57
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${mail.send.from}")
    private String sendFrom;

    @Override
    public void send(String subject, String text, String... sendTo) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setFrom(sendFrom);
            helper.setTo(sendTo);
            helper.setSubject(subject);
            helper.setText(text);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("email msg create failed: {}", e.getMessage());
        } catch (MailSendException ex) {
            logger.error("send email failed: {}", ex.getMessage());
        }
    }

    @Override
    public void sendAttachment(String subject, String text, String attachmentPath, String... sendTo) {

    }
}
