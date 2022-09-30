package com.stackroute.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "RestaurantBill")
public class Bill {

    @Id
    private String paymentId;
    private String bookingId;
    private String bookingDate;
    private String userId;
    private String payerEmail;
    private String payerFirstName;
    private String payerLastName;
    private String paymentMethod;
    private String currency;
    private String totalAmount;
    private String paymentMode;
    private String paymentStatus;
    private LocalDateTime paymentTime;



}
