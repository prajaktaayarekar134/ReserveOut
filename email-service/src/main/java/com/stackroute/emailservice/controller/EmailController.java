package com.stackroute.emailservice.controller;
import com.stackroute.emailservice.Entity.EmailDetails;
import com.stackroute.emailservice.Exception.MailSendException;
import com.stackroute.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class EmailController {
    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details){
        try {
            String status
                    = emailService.sendSimpleMail(details);

            return status;
        } catch (MailSendException e) {
            return "Error While Sending Mail";
        }
    }
}