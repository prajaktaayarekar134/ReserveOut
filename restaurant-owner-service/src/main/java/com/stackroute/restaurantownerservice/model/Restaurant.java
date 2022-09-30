package com.stackroute.restaurantownerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Restaurant_Detail")
public class Restaurant {
    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantType;
    private Integer noOfTable;
    private String restaurantAddress;
    private String restaurantCuisine;
    private Integer tableBookingPrice;
    private String ownerName;
    private String email;
    private Date restaurantTimings;
    private String restaurantContact;
    private String restaurantApi;

}
