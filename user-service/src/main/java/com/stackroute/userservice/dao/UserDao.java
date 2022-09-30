package com.stackroute.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.userservice.model.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Long>{

}
