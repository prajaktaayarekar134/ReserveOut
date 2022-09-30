package com.stackroute.paymentservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDetail {

     private String bookingId;
     private String restaurantId;
     private String userId;
     private String numberOfSeats;
     private String bookingDate;
     private String currency;
     private Double tableBookingPrice;
}
