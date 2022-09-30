package com.stackroute.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WishlistNotFound extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public WishlistNotFound(String message) {
        super(message);
    }
}
