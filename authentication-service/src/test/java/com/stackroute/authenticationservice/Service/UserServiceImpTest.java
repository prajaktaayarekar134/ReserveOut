package com.stackroute.authenticationservice.Service;

import static com.stackroute.authenticationservice.entities.Role.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.authenticationservice.Dao.UserDao;
import com.stackroute.authenticationservice.entities.Role;
import com.stackroute.authenticationservice.entities.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotRegister;
import com.stackroute.authenticationservice.request.LoginRequest;
import com.stackroute.authenticationservice.request.ResetRequest;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImp.class})
@ExtendWith(SpringExtension.class)
class UserServiceImpTest {
    @MockBean
    private UserDao userDao;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private UserService userService;

    @org.junit.jupiter.api.Test
    public void registerNewUserTest() throws UserAlreadyExistsException {
        User user3 = User.builder()
                .userName("jpsahni")
                .emailId("jpsahni1997@gmail.com")
                .role(user)
                .password("Jpssk1243")
                .build();
        Mockito.when(userDao.findById(any())).thenReturn(Optional.empty());

        Mockito.when(userDao.save(any())).thenReturn(user3);
        assertEquals(userService.registerNewUser(user3),user3);
    }

    @org.junit.jupiter.api.Test
    public void resetPasswordTest() throws UserNotRegister {

        ResetRequest resetRequest = ResetRequest.builder()
                .emailId("jpsahni01997@gmail.com")
                .password("Jpssp12345")
                .passwordConfirmation("Jpssp12345")
                .build();
        User user3=new User();
        Mockito.when(userDao.findById(any())).thenReturn(Optional.of(user3));

        String str="Your New Password is "+resetRequest.getPassword();

        Mockito.when(userDao.save(any())).thenReturn(user3);

        assertEquals(userService.reset(resetRequest),str);
    }



    @Test
    void testRegisterNewUser() throws UserAlreadyExistsException {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");

        User user1 = new User();
        user1.setEmailId("42");
        user1.setPassword("iloveyou");
        user1.setRole(Role.user);
        user1.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user1);
        when(userDao.save((User) any())).thenReturn(user);
        when(userDao.findById((String) any())).thenReturn(ofResult);

        User user2 = new User();
        user2.setEmailId("42");
        user2.setPassword("iloveyou");
        user2.setRole(Role.user);
        user2.setUserName("janedoe");
        assertThrows(UserAlreadyExistsException.class, () -> userServiceImp.registerNewUser(user2));
        verify(userDao).findById((String) any());
    }

    @Test
    void testRegisterNewUser2() throws UserAlreadyExistsException {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        when(userDao.save((User) any())).thenReturn(user);
        when(userDao.findById((String) any())).thenReturn(Optional.empty());

        User user1 = new User();
        user1.setEmailId("42");
        user1.setPassword("iloveyou");
        user1.setRole(Role.user);
        user1.setUserName("janedoe");
        assertSame(user, userServiceImp.registerNewUser(user1));
        verify(userDao).save((User) any());
        verify(userDao).findById((String) any());
    }


    @Test
    void testReset() throws UserNotRegister {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setEmailId("42");
        user1.setPassword("iloveyou");
        user1.setRole(Role.user);
        user1.setUserName("janedoe");
        when(userDao.save((User) any())).thenReturn(user1);
        when(userDao.findById((String) any())).thenReturn(ofResult);
        ResetRequest resetRequest = mock(ResetRequest.class);
        when(resetRequest.getEmailId()).thenReturn("42");
        when(resetRequest.getPassword()).thenReturn("iloveyou");
        assertEquals("Your New Password is iloveyou", userServiceImp.reset(resetRequest));
        verify(userDao).save((User) any());
        verify(userDao).findById((String) any());
        verify(resetRequest).getEmailId();
        verify(resetRequest, atLeast(1)).getPassword();
    }

    @Test
    void testReset2() throws UserNotRegister {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        when(userDao.save((User) any())).thenReturn(user);
        when(userDao.findById((String) any())).thenReturn(Optional.empty());
        ResetRequest resetRequest = mock(ResetRequest.class);
        when(resetRequest.getEmailId()).thenReturn("42");
        when(resetRequest.getPassword()).thenReturn("iloveyou");
        assertThrows(UserNotRegister.class, () -> userServiceImp.reset(resetRequest));
        verify(userDao).findById((String) any());
        verify(resetRequest).getEmailId();
    }





    @Test
    void testLoginUser() throws UserNotRegister {
        User user = new User();
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findById((String) any())).thenReturn(ofResult);
        LoginRequest loginRequest = mock(LoginRequest.class);
        when(loginRequest.getPassword()).thenReturn("iloveyou");
        when(loginRequest.getUserId()).thenReturn("42");
        assertEquals("LogIn successfully", userServiceImp.loginUser(loginRequest));
        verify(userDao).findById((String) any());
        verify(loginRequest).getPassword();
        verify(loginRequest).getUserId();
    }

    @Test
    void testLoginUser2() throws UserNotRegister {
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("foo");
        doNothing().when(user).setEmailId((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserName((String) any());
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userDao.findById((String) any())).thenReturn(ofResult);
        LoginRequest loginRequest = mock(LoginRequest.class);
        when(loginRequest.getPassword()).thenReturn("iloveyou");
        when(loginRequest.getUserId()).thenReturn("42");
        assertEquals("Invalid password", userServiceImp.loginUser(loginRequest));
        verify(userDao).findById((String) any());
        verify(user).getPassword();
        verify(user).setEmailId((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRole((Role) any());
        verify(user).setUserName((String) any());
        verify(loginRequest).getPassword();
        verify(loginRequest).getUserId();
    }

    /**
     * Method under test: {@link UserServiceImp#loginUser(LoginRequest)}
     */
    @Test
    void testLoginUser3() throws UserNotRegister {
        when(userDao.findById((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setEmailId((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRole((Role) any());
        doNothing().when(user).setUserName((String) any());
        user.setEmailId("42");
        user.setPassword("iloveyou");
        user.setRole(Role.user);
        user.setUserName("janedoe");
        LoginRequest loginRequest = mock(LoginRequest.class);
        when(loginRequest.getPassword()).thenReturn("iloveyou");
        when(loginRequest.getUserId()).thenReturn("42");
        assertThrows(UserNotRegister.class, () -> userServiceImp.loginUser(loginRequest));
        verify(userDao).findById((String) any());
        verify(user).setEmailId((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRole((Role) any());
        verify(user).setUserName((String) any());
        verify(loginRequest).getUserId();
    }
}

