package com.example.bjosefow_22_mvc_szyblony;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    private final MailService mailService;
    private final String companyEmailAdress = "przykladowe991122@op.pl";

    public PageController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    String home() {
        return "homePage";
    }

    @GetMapping("/contactForm")
    String contact(Model model) {
        model.addAttribute("mailMessage", new ContactMessage());
        return "contactPage";
    }

    @PostMapping("/send")
    String send(ContactMessage mailMessage) {
        mailMessage.setSender(companyEmailAdress);
        mailMessage.setReceiver(companyEmailAdress);
        boolean send = mailService.sendBasicMail(mailMessage);
        if (send) {
            mailService.sendConfirmationMail(mailMessage);
            return "redirect:/success";
        } else {
            return "redirect:/failure";
        }
    }

    @GetMapping("/success")
    String messageSent() {
        return "summaryPage";
    }

    @GetMapping("/failure")
    String messageNotSent() {
        return "summaryPageFailure";
    }

}
