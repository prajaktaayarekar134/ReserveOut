package com.stackroute.emailservice.service;
import com.stackroute.emailservice.config.MessageConfig;
import com.stackroute.emailservice.dto.BillMessage;
import com.stackroute.emailservice.Entity.EmailDetails;
import com.stackroute.emailservice.Exception.MailSendException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;

    private static String booingId;
    private static String userId;
    private static String paymentId;
    private static String tableBookingPrice;
    private static Double bookingPrice;
    private static String bookingDate;

    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {
        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText("Hi User " + ",\n\n" + "Your Table is booked successfully and Details of Bookings are " +
                    "Booking Id : " + booingId + " , User Id : " + userId + " ,Booking Date : " + bookingDate + ", Table Price : " + bookingPrice +" Rupee"
                    + ", Payment Id : " + paymentId + ".\n\n" + "Thanks & Regards\nSpotYourVaccine");
            mailMessage.setSubject("Status of Booking ");
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";

        }
        // Catch block to handle the exceptions
        catch (MailSendException e) {
            return "Error while Sending Mail";
        }
    }

    @RabbitListener(queues = MessageConfig.QUEUE)
    public void listener(BillMessage bill) {
        booingId = bill.getBookId();
        userId = bill.getUserId();
        bookingDate=bill.getBookingDate();
        paymentId = bill.getPayerPaymentId();
        tableBookingPrice = bill.getTableBookingPrice();

        System.out.println(bill.getTableBookingPrice());

        bookingPrice=Double.parseDouble(tableBookingPrice) * 80;




    }
}
