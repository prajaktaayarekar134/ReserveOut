package com.stackroute.recommendationservice.model;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "Restaurant_Detail")
public class RestaurantEntity {


    @Id
    private String restaurantId;
    private String restaurantName;
    private String restaurantType;
    private String noOfTable;
    private String restaurantAddress;
    private String restaurantCuisine;
    private Integer tableBookingPrice;
    private String ownerName;
    private String email;
    private LocalDateTime restaurantTimings;
    private String restaurantContact;
    private String restaurantApi;
    //localhost:8585/api/findById/102


}
