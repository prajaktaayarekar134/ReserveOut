package com.stackroute.restaurantownerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestaurantControllerAdvice {
    @ExceptionHandler(RestaurantNotFound.class)
    public ResponseEntity<String> restaurantNotFound(RestaurantNotFound restaurantNotFound) {
        return new ResponseEntity<>("Restaurant Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInput.class)
    public ResponseEntity<String> invalidInput(RestaurantNotFound invalidInput) {
        return new ResponseEntity<>("Invalid input,Please Check", HttpStatus.BAD_REQUEST);
    }
}