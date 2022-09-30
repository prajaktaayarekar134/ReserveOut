package com.stackroute.paymentservice.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.paymentservice.model.BookingDetail;
import com.stackroute.paymentservice.rabbitmqConfig.MQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private APIContext apiContext;



	private static String currency="INR";
	private static Double tableBookingPrice;

	@RabbitListener(queues = MQConfig.QUEUE1)
	public void listener(BookingDetail bookingDetail) {

	tableBookingPrice=bookingDetail.getTableBookingPrice();
	System.out.println(tableBookingPrice);
	System.out.println(bookingDetail.getBookingId());
	System.out.println(bookingDetail.getBookingDate());
	System.out.println(bookingDetail.getUserId());


	}






	public Payment createPayment(BookingDetail order, String cancelUrl, String successUrl) throws PayPalRESTException {

		apiContext.setRequestId(null);





		Payment payment = setPayment(getTransactions(setAmount(currency,tableBookingPrice)), cancelUrl,
				successUrl);

		return payment.create(apiContext);
	}

	public Amount setAmount(String currency, double total) {
		if (currency.equals("INR")) {
			currency = "USD";
			total = total / 80;
		}

		Amount amount = new Amount();
		amount.setCurrency(currency);
		total = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
		amount.setTotal(String.valueOf(total));

		return amount;
	}

	public List<Transaction> getTransactions(Amount amount) {
		Transaction transaction = new Transaction();
		transaction.setDescription("Table Booking");
		transaction.setAmount(amount);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);
		return transactions;
	}

	public Payment setPayment(List<Transaction> transactions, String cancelUrl, String successUrl) {



		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");


		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(successUrl);
		payment.setRedirectUrls(redirectUrls);
		return payment;
	}

	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {

		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		return payment.execute(apiContext, paymentExecute);
	}
}
