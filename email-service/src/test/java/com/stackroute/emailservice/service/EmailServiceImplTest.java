package com.stackroute.emailservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import com.stackroute.emailservice.Entity.EmailDetails;
import com.stackroute.emailservice.dto.BillMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EmailServiceImplTest {
    @Autowired
    private EmailServiceImpl emailServiceImpl;

    @MockBean
    private JavaMailSender javaMailSender;
    @Test
    void sendSimpleMail_Success() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        assertEquals("Mail Sent Successfully...", emailServiceImpl.sendSimpleMail(new EmailDetails("Recipient")));
        verify(javaMailSender).send((SimpleMailMessage) any());
    }
//    @Test
//    void Listener_Success() {
//        emailServiceImpl.listener(new BillMessage("42", "42", "42", "Table Booking Price"));
//        assertEquals("42", EmailServiceImpl.booingId);
//    }

}