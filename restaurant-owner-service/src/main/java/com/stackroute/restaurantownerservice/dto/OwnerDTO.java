package com.stackroute.restaurantownerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OwnerDTO {
    private String restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantContact;


}
