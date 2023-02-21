package com.example.bjosefow_22_mvc_szyblony;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    private final MailService mailService;
    private final String companyEmailAdress = "firmowy@byom.de";

    public PageController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/")
    String home(Model model) {
        model.addAttribute("mailMessage", new SimpleMailMessage());
        return "contactPage";
    }

    @PostMapping("/send")
    String send(SimpleMailMessage mailMessage) {
        mailMessage.setTo(companyEmailAdress);
        boolean send = mailService.sendMail(mailMessage);
        if (send) {
            return "redirect:/success";
        } else {
            return "redirect:/failure";
        }
    }

    @GetMapping("/success")
    String messageSent(Model model) {
        model.addAttribute("replyTo", mailService.getReplyToEMailAddress());
        return "summaryPage";
    }

    @GetMapping("/failure")
    String messageNotSent() {
        return "summaryPageFailure";
    }

}
