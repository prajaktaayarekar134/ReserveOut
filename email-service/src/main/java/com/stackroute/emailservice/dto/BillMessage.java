package com.stackroute.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillMessage {

    private String bookId;
    private String userId;
    private String bookingDate;
    private String payerPaymentId;
    private String tableBookingPrice;

}
