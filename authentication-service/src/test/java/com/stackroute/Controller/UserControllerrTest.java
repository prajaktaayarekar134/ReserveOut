package com.stackroute.Controller;

import com.stackroute.authenticationservice.AuthenticationServiceApplication;
import com.stackroute.authenticationservice.Controller.UserController;
import com.stackroute.authenticationservice.Service.UserService;
import com.stackroute.authenticationservice.entities.AuthRequest;
import com.stackroute.authenticationservice.entities.User;

import com.stackroute.authenticationservice.request.LoginRequest;
import com.stackroute.authenticationservice.request.ResetRequest;
import com.stackroute.authenticationservice.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.stackroute.authenticationservice.entities.Role.user;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = AuthenticationServiceApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerrTest {

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;
    @MockBean
    JwtUtil jwtUtil;
    @Autowired
    private UserController userController;

    @Test
    public void registerNewUserTest() throws Exception {

        User user3 = User.builder()
                .userName("jpsahni")
                .emailId("jpsahni1997@gmail.com")
                .role(user)
                .password("Jpssk1243")
                .build();


        // User user1 = null;
        // Mockito.when(userService.registerNewUser(user3)).thenReturn(user1);

        Mockito.when(userService.registerNewUser(user3)).thenReturn(user3);




        assertEquals(userController.registerNewUser(user3).getStatusCode().value(), 201);
    }

    @Test
    public void resetPasswordTest() throws  Exception{

        ResetRequest resetRequest =  ResetRequest.builder()
                .emailId("jpsahni01997@gmail.com")
                .password("Jpssp12345")
                .passwordConfirmation("Jpssp12345")
                .build();
        
       String str = "Your New Password is "+resetRequest.getPassword();
        //String str = null;
        Mockito.when(userService.reset(resetRequest)).thenReturn(str);

        assertEquals(userController.reset(resetRequest),str);

    }

  @Test
    public void  generateTokenTest() throws Exception{

        AuthRequest authRequest = AuthRequest.builder()
                .userName("jpsahni")
                .password("Jpssk1243")
                .build();

      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
      );

        String str=null;

        Mockito.when(jwtUtil.generateToken(authRequest.getUserName())).thenReturn(str);


        assertEquals(userController.generateToken(authRequest),str);


    }

    @Test
    public void loginTest() throws Exception{
        LoginRequest loginRequest = LoginRequest.builder()
                .userId("jpsahni01997@gmail.com")
                .password("Jpssp1234")
                .build();
    String str=null;
        Mockito.when(userService.loginUser(loginRequest)).thenReturn(str);

        assertEquals(userController.loginUser(loginRequest),str);


    }




}
