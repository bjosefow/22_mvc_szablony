package com.example.bjosefow_22_mvc_szyblony;
import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.MessageExpression;

import java.util.LinkedList;
import java.util.List;

@Service
public class MailService  {

    private JavaMailSender javaMailSender;
    private String replyToEMailAddress;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendMail(SimpleMailMessage mail) {
        try {
            javaMailSender.send(mail);
            replyToEMailAddress = mail.getReplyTo();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getReplyToEMailAddress() {
        return replyToEMailAddress;
    }
}
