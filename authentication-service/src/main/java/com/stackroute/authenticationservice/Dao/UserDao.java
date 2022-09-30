package com.stackroute.authenticationservice.Dao;

import com.stackroute.authenticationservice.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User,String> {
     User findByUserName(String username);
}
