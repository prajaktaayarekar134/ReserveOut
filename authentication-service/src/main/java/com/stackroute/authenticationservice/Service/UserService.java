package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.entities.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotRegister;
import com.stackroute.authenticationservice.request.LoginRequest;
import com.stackroute.authenticationservice.request.ResetRequest;
import org.springframework.web.bind.annotation.RequestBody;


public interface UserService {
    User registerNewUser(User user) throws UserAlreadyExistsException;

    String loginUser(LoginRequest loginRequest) throws UserNotRegister;

    String reset( @RequestBody ResetRequest resetRequest) throws UserNotRegister;
}
