package com.example.bjosefow_22_mvc_szyblony;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService  {

    private JavaMailSender javaMailSender;
    private final String confirmationMailTitle = "Potwierdzenie wyslania wiadomosci";
    private final String confirmationMailContent = "Twoja wiadomosc zostala wyslana do Przedszkola 'Kwiatuszek'. Dziekujemy za kontakt";

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public boolean sendBasicMail(ContactMessage mail) {

        //mail pierwotny
        // od kogo  techniczny
        // do kogo  techniczny
        // nadawca /odpowiedz do  tutaj mamy do kogo potwierdzenie wyslac
        // tresc
        // tytul

        SimpleMailMessage basicMail = new SimpleMailMessage();
        basicMail.setFrom(mail.getSender());
        basicMail.setTo(mail.getReceiver());
        basicMail.setReplyTo(mail.getReplyTo());
        basicMail.setSubject(mail.getTitle());
        basicMail.setText(mail.getContent());

        try {
            javaMailSender.send(basicMail);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendConfirmationMail(ContactMessage mail) {

        // Potwierdzenie
        // od kogo -> techniczny
        // do kogo -> nadawca/odpowiedz do
        // tytul (Potwierduenie wyslania wiadomosci do Przedszola kwiatuszek)
        // tresc
        // odpowedz do
        // ustawia sie na to od kogo

        SimpleMailMessage confirmationMail = new SimpleMailMessage();
        confirmationMail.setFrom(mail.getSender());
        confirmationMail.setTo(mail.getReplyTo());
        confirmationMail.setReplyTo(mail.getSender());
        confirmationMail.setSubject(confirmationMailTitle);
        confirmationMail.setText(confirmationMailContent);
        javaMailSender.send(confirmationMail);
    }
}
