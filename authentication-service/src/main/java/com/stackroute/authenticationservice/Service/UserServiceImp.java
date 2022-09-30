package com.stackroute.authenticationservice.Service;

import com.stackroute.authenticationservice.Dao.UserDao;
import com.stackroute.authenticationservice.entities.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistsException;
import com.stackroute.authenticationservice.exception.UserNotRegister;
import com.stackroute.authenticationservice.request.LoginRequest;
import com.stackroute.authenticationservice.request.ResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;
@Override
    public User registerNewUser(User user) throws UserAlreadyExistsException {
        Optional<User> optional=userDao.findById(user.getEmailId());
        if(optional.isPresent()){
            throw new UserAlreadyExistsException("user already exist");
        }
        return userDao.save(user);

    }


    @Override
    public String reset(ResetRequest resetRequest) throws UserNotRegister{
        Optional<User> optional=userDao.findById(resetRequest.getEmailId());
        if(optional.isEmpty()){
            throw new UserNotRegister("user Not Register");
        }
        User user = optional.get();
        user.setPassword(resetRequest.getPassword());
        userDao.save(user);

        return "Your New Password is "+resetRequest.getPassword();
    }

    @Override
    public String loginUser(LoginRequest loginRequest) throws UserNotRegister {
        Optional<User> optional=userDao.findById(loginRequest.getUserId());
        if(optional.isEmpty()){
            throw new UserNotRegister("user Not Register");
        }
        User user = optional.get();
        //user.setPassword();

        if(user.getPassword().equals(loginRequest.getPassword())) {
            return "LogIn successfully";
        } else {
            return "Invalid password";
        }

    }





}
