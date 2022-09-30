package com.stackroute.recommendationservice.model;

import lombok.Data;

@Data
public class RestaurantMessage {
    private String restaurantId;
    private String restaurantName;
    private Integer tableBookingPrice;
}
