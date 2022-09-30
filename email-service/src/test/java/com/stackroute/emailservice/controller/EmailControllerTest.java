package com.stackroute.emailservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.emailservice.Entity.EmailDetails;
import com.stackroute.emailservice.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {EmailController.class})
@ExtendWith(SpringExtension.class)
class EmailControllerTest {
    @Autowired
    private EmailController emailController;
    @MockBean
    private EmailService emailService;

    @Test
    void sendMail_Success() throws Exception {
        when(emailService.sendSimpleMail((EmailDetails) any())).thenReturn("Send Simple Mail");

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("Recipient");
        String content = (new ObjectMapper()).writeValueAsString(emailDetails);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/sendMail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(emailController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Send Simple Mail"));
    }
}

