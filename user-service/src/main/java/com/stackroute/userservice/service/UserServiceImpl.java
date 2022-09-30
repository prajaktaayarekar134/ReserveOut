package com.stackroute.userservice.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.dao.UserDao;
import com.stackroute.userservice.model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;

	
	public UserServiceImpl() {
		
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		
		return userdao.findAll();
	}

	@Override
	public User getUser(long userId) {
		List<User> user= userdao.findAll();
		User user1=null;
		for(User u :user){
			if(u.getUserId()==userId)
				user1=u;
		}

		return user1;
		
	}

	@Override
	public User addUser(User user) {

		userdao.save(user);
		return user;
	}

	@Override
	public void deleteUser(long userId) {
		List<User> user= userdao.findAll();
		User user1=null;
		for(User u :user){
			if(u.getUserId()==userId)
				user1=u;
		}
		userdao.delete(user1);
	}



	@Override
	public User updateUser(User user) {

		userdao.save(user);
		return user;
	}
		
}
