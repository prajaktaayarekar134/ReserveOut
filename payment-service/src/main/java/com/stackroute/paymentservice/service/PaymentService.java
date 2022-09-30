package com.stackroute.paymentservice.service;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.paymentservice.model.BookingDetail;


public interface PaymentService {
	
	Payment createPayment(BookingDetail order, String cancelUrl, String successUrl) throws PayPalRESTException;
	Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;

}
