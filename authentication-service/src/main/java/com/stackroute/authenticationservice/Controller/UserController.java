package com.stackroute.authenticationservice.Controller;

import com.stackroute.authenticationservice.Service.UserService;
import com.stackroute.authenticationservice.entities.AuthRequest;
import com.stackroute.authenticationservice.entities.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotRegister;
import com.stackroute.authenticationservice.request.LoginRequest;
import com.stackroute.authenticationservice.request.ResetRequest;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) throws UserNotRegister {
        return userService.loginUser(loginRequest);

    }

    @GetMapping("/home")
    public String home()
    {
        return "Welcome to ReserveOut ";
    }



    @PostMapping("/registerNewUser")
    public ResponseEntity<User> registerNewUser(@RequestBody @Valid User user) throws UserAlreadyExistsException{
        //encoding password with BCryptPasswordEncoder
         //user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));


        User user1 = userService.registerNewUser(user);
        return new ResponseEntity<>(user1,HttpStatus.CREATED);
    }
    @PostMapping("/reset")
    public String reset( @RequestBody @Valid ResetRequest resetRequest) throws UserNotRegister {

        return userService.reset(resetRequest);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid username/password");
        }
         return jwtUtil.generateToken(authRequest.getUserName());
    }




}
