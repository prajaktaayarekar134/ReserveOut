package com.stackroute.restaurantownerservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Data
@Component
public class RestaurantNotFound extends RuntimeException{
    private HttpStatus httpStatus;
    private String errorMessage;
}
