package com.stackroute.paymentservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;


import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.base.rest.APIContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ContextConfiguration(classes = {PaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PaymentServiceImplTest {
    @MockBean
    private APIContext apiContext;
    
    @MockBean
    private Payment payment;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;


    @Test
    void testSetAmount() {
        Amount actualSetAmountResult = paymentServiceImpl.setAmount("USD", 10.0f);
        assertEquals("USD", actualSetAmountResult.getCurrency());
        assertEquals("10.0", actualSetAmountResult.getTotal());
    }
  
    @Test
    void testGetTransactions() {
        assertEquals(1, paymentServiceImpl.getTransactions(new Amount("USD", "Total")).size());
    }

    @Test
    void testSetPayment() {
        Payment actualSetPaymentResult = paymentServiceImpl.setPayment(new ArrayList<>(), "https://localhost:/pay/cancel",
                "https://localhost:/pay/success");
        assertTrue(actualSetPaymentResult.getTransactions().isEmpty());
        assertEquals("sale", actualSetPaymentResult.getIntent());
        RedirectUrls redirectUrls = actualSetPaymentResult.getRedirectUrls();
        assertEquals("https://localhost:/pay/success", redirectUrls.getReturnUrl());
        assertEquals("https://localhost:/pay/cancel", redirectUrls.getCancelUrl());
        assertEquals("paypal", actualSetPaymentResult.getPayer().getPaymentMethod());
    }
}

