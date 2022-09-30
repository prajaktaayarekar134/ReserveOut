package com.stackroute.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    private String restaurantId;

    @NotEmpty(message = "Restaurant Name should not be null or empty")
    private String restaurantName;

    @NotEmpty(message = "Restaurant location should not be null or empty")
    private String location;

    @NotEmpty(message = "Restaurant contact should not be null or empty")
    private String contact;

}
