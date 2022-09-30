package com.stackroute.emailservice.service;
import com.stackroute.emailservice.Entity.EmailDetails;

public interface EmailService {
    String sendSimpleMail(EmailDetails emailDetails);
        }
