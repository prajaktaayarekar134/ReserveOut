package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;

import java.util.List;

//import com.stackroute.userservice.model.*;

public interface UserService {
	 List<User> getUsers();
	
	 User getUser(long userId);
	
	 User addUser(User user);
	
	 void deleteUser(long userId);
	
	 User updateUser(User user);
}
