package com.stackroute.paymentservice.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.stackroute.paymentservice.exception.BillNotFoundException;
import com.stackroute.paymentservice.model.Bill;
import com.stackroute.paymentservice.model.BillMessage;
import com.stackroute.paymentservice.model.BookingDetail;
import com.stackroute.paymentservice.model.PaymentDetails;
import com.stackroute.paymentservice.rabbitmqConfig.MQConfig;
import com.stackroute.paymentservice.service.BillService;
import com.stackroute.paymentservice.service.PaymentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;



@RestController
@RequestMapping("/api")
public class PaymentController {

    private static String bookingId;
    private static String userId;
    private static String bookingDate;



    private PaymentService payservice;
    private BillService iservice;

    @Autowired
    public PaymentController(PaymentService payservice, BillService iservice) {
        this.iservice = iservice;
        this.payservice = payservice;
    }

    //publish
    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = MQConfig.QUEUE1)
    public void listener(BookingDetail bookingDetail) {
        bookingId=bookingDetail.getBookingId();
        userId=bookingDetail.getUserId();
        bookingDate=bookingDetail.getBookingDate();

        System.out.println(bookingDate);
        System.out.println(bookingId);
        System.out.println(userId);



    }
//


    @Value("${server.port}")
    public String port;

    @PostMapping("/payment")
    public ResponseEntity<String> payment() {
        try {
            BookingDetail order = new BookingDetail();

            Payment payment = payservice.createPayment(order,
                    "http://localhost:" + port + "/api" + "/payment/cancel",
                    "http://localhost:" + port + "/api" + "/payment/success");
            for (Links link : payment.getLinks()) {
                if (link.getRel().equals("approval_url")) {
                    return new ResponseEntity<>(link.getHref(), HttpStatus.ACCEPTED);
                }
            }

        } catch (PayPalRESTException paypalrest) {
            return new ResponseEntity<>("Exception occurred, please contact support team", HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Could not initiate payment, please try after some time", HttpStatus.CONFLICT);

    }

    //@GetMapping("/payment/cancel")
    public ResponseEntity<String> paymentCancelled() {
        return new ResponseEntity<>("Payment cancelled", HttpStatus.OK);
    }
    @GetMapping("/payment/success")
    public ResponseEntity<String> paymentSuccessful(@RequestParam("paymentId") String paymentId,
                                                    @RequestParam("PayerID") String payerId) {
        ResponseEntity<String> response;
        try {
            if (paymentId == null || payerId == null || paymentId.isEmpty() || payerId.isEmpty()) {
                return new ResponseEntity<>("could not get payment details", HttpStatus.BAD_GATEWAY);
            }
            Payment payment = payservice.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {

                Bill b = new Bill();
                b.setBookingId(bookingId);
                b.setUserId(userId);
                b.setBookingDate(bookingDate);
                b.setPaymentId(payment.getId());
                b.setPayerEmail(payment.getPayer().getPayerInfo().getEmail());
                b.setPayerFirstName(payment.getPayer().getPayerInfo().getFirstName());
                b.setPayerLastName(payment.getPayer().getPayerInfo().getLastName());
                b.setPaymentMethod(payment.getPayer().getPaymentMethod());
                b.setPaymentMode(
                        payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getPaymentMode());
                b.setCurrency(payment.getTransactions().get(0).getAmount().getCurrency());
                b.setTotalAmount(payment.getTransactions().get(0).getAmount().getTotal());
                b.setPaymentStatus(payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getState());
                b.setPaymentTime(LocalDateTime.now());
                iservice.saveBill(b);

                //   publish
                BillMessage msg = new BillMessage();
                msg.setBookId(bookingId);
                msg.setUserId(userId);
                msg.setBookingDate(bookingDate);
                msg.setPayerPaymentId(payment.getId());
                msg.setTableBookingPrice(payment.getTransactions().get(0).getAmount().getTotal());
                template.convertAndSend(MQConfig.EXCHANGE,
                        MQConfig.ROUTING_KEY, msg);



                response = new ResponseEntity<>("Payment successful", HttpStatus.OK);




            } else {
                response = new ResponseEntity<>("Payment failed", HttpStatus.BAD_GATEWAY);
            }
        } catch (PayPalRESTException paypalrest) {
            response = new ResponseEntity<>("Exception occurred, please contact support team", HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/getBillDetails/{paymentId}")
    public ResponseEntity<?> getBillDetails(@PathVariable String paymentId) {
        try {
            return new ResponseEntity<>(iservice.getBillDetailsByPaymentId(paymentId), HttpStatus.OK);
        } catch (BillNotFoundException i) {
            return new ResponseEntity<>(i.getErrorMessage(), HttpStatus.CONFLICT);
        } catch (Exception ex) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }

    }
}
