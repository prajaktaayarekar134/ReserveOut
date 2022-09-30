package com.stackroute.paymentservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.paymentservice.exception.BillNotFoundException;
import com.stackroute.paymentservice.model.Bill;
import com.stackroute.paymentservice.model.BookingDetail;
import com.stackroute.paymentservice.service.BillService;
import com.stackroute.paymentservice.service.PaymentService;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PaymentController.class})
@ExtendWith(SpringExtension.class)
class PaymentControllerTest {
    @MockBean
    private BillService billService;

    @Autowired
    private PaymentController paymentController;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * Method under test: {@link PaymentController#listener(BookingDetail)}
     */
    @Test
    void testListener() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        paymentController.listener(new BookingDetail());
    }

    /**
     * Method under test: {@link PaymentController#listener(BookingDetail)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testListener2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.stackroute.paymentservice.controller.PaymentController.listener(PaymentController.java:51)
        //   In order to prevent listener(BookingDetail)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   listener(BookingDetail).
        //   See https://diff.blue/R013 to resolve this issue.

        paymentController.listener(null);
    }

    /**
     * Method under test: {@link PaymentController#listener(BookingDetail)}
     */
    @Test
    void testListener3() {
        BookingDetail bookingDetail = mock(BookingDetail.class);
        when(bookingDetail.getBookingDate()).thenReturn("2020-03-01");
        when(bookingDetail.getBookingId()).thenReturn("42");
        when(bookingDetail.getUserId()).thenReturn("42");
        paymentController.listener(bookingDetail);
        verify(bookingDetail).getBookingDate();
        verify(bookingDetail).getBookingId();
        verify(bookingDetail).getUserId();
    }

    /**
     * Method under test: {@link PaymentController#paymentCancelled()}
     */
    @Test
    void testPaymentCancelled() {
        ResponseEntity<String> actualPaymentCancelledResult = paymentController.paymentCancelled();
        assertEquals("Payment cancelled", actualPaymentCancelledResult.getBody());
        assertEquals(HttpStatus.OK, actualPaymentCancelledResult.getStatusCode());
        assertTrue(actualPaymentCancelledResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link PaymentController#getBillDetails(String)}
     */
    @Test
    void testGetBillDetails() throws Exception {
        Bill bill = new Bill();
        bill.setBookingDate("2020-03-01");
        bill.setBookingId("42");
        bill.setCurrency("GBP");
        bill.setPayerEmail("jane.doe@example.org");
        bill.setPayerFirstName("Jane");
        bill.setPayerLastName("Doe");
        bill.setPaymentId("42");
        bill.setPaymentMethod("Payment Method");
        bill.setPaymentMode("Payment Mode");
        bill.setPaymentStatus("Payment Status");
        bill.setPaymentTime(LocalDateTime.of(1, 1, 1, 1, 1));
        bill.setTotalAmount("10");
        bill.setUserId("42");
        when(billService.getBillDetailsByPaymentId((String) any())).thenReturn(bill);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getBillDetails/{paymentId}", "42");
        MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"paymentId\":\"42\",\"bookingId\":\"42\",\"bookingDate\":\"2020-03-01\",\"userId\":\"42\",\"payerEmail\":\"jane.doe"
                                        + "@example.org\",\"payerFirstName\":\"Jane\",\"payerLastName\":\"Doe\",\"paymentMethod\":\"Payment Method\",\"currency"
                                        + "\":\"GBP\",\"totalAmount\":\"10\",\"paymentMode\":\"Payment Mode\",\"paymentStatus\":\"Payment Status\",\"paymentTime"
                                        + "\":[1,1,1,1,1]}"));
    }

    /**
     * Method under test: {@link PaymentController#getBillDetails(String)}
     */
    @Test
    void testGetBillDetails2() throws Exception {
        when(billService.getBillDetailsByPaymentId((String) any()))
                .thenThrow(new BillNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getBillDetails/{paymentId}",
                "42");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("An error occurred"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment() throws Exception {
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any()))
                .thenReturn(new Payment());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bad Request"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment2() throws Exception {
        Payment payment = new Payment();
        payment.setLinks(new ArrayList<>());
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any())).thenReturn(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Could not initiate payment, please try after some time"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment3() throws Exception {
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bad Request"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment4() throws Exception {
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any()))
                .thenThrow(new PayPalRESTException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Exception occurred, please contact support team"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment5() throws Exception {
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any()))
                .thenThrow(new BillNotFoundException("An error occurred"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bad Request"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment6() throws Exception {
        ArrayList<Links> linksList = new ArrayList<>();
        linksList.add(new Links("?", "?"));

        Payment payment = new Payment();
        payment.setLinks(linksList);
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any())).thenReturn(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(409))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Could not initiate payment, please try after some time"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment7() throws Exception {
        ArrayList<Links> linksList = new ArrayList<>();
        linksList.add(new Links());

        Payment payment = new Payment();
        payment.setLinks(linksList);
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any())).thenReturn(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bad Request"));
    }

    /**
     * Method under test: {@link PaymentController#payment()}
     */
    @Test
    void testPayment8() throws Exception {
        ArrayList<Links> linksList = new ArrayList<>();
        linksList.add(null);

        Payment payment = new Payment();
        payment.setLinks(linksList);
        when(paymentService.createPayment((BookingDetail) any(), (String) any(), (String) any())).thenReturn(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/payment");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Bad Request"));
    }

    /**
     * Method under test: {@link PaymentController#paymentSuccessful(String, String)}
     */
    @Test
    void testPaymentSuccessful() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/payment/success")
                .param("payerId", "foo")
                .param("paymentId", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(paymentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

