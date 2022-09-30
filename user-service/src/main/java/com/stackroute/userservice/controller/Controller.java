package com.stackroute.userservice.controller;

import java.util.List;

import com.stackroute.userservice.exceptions.InvalidInput;
import com.stackroute.userservice.exceptions.UserNotFound;
import com.stackroute.userservice.exceptions.WishlistNotFound;
//import com.stackroute.userservice.model.*;
//import com.stackroute.userservice.service.*;

import com.stackroute.userservice.model.Restaurant;
import com.stackroute.userservice.service.RestaurantService;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.stackroute.userservice.model.User;


@RestController
@RequestMapping("/api")
public class Controller {

	
	@Autowired
	private UserService userService;

	@Autowired
	private RestaurantService restaurantService;


	@PostMapping("/wishlist")
	public Restaurant addRestuarant(@RequestBody Restaurant restaurant){
		try {
			return this.restaurantService.addRestaurant(restaurant);
		}catch (Exception e){
			throw new InvalidInput();
		}
	}

	@GetMapping("/wishlist")
	public List<Restaurant> getWishlist(){
		try{
			return restaurantService.getRestaurant();
		}catch(Exception e){
			throw new WishlistNotFound("Wishlist Not found");
		}
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		try {
			return userService.getUsers();
		} catch (Exception e) {
			throw new UserNotFound("User Not Found");

		}
	}



	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable String userId) {
		try {
			return this.userService.getUser(Long.parseLong(userId));
		}catch(Exception e){
			throw new UserNotFound("User Not Found");
		}
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		try {

			return this.userService.addUser(user);
		}catch(Exception e){
			throw new InvalidInput();
		}
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		try {
			return this.userService.updateUser(user);
		}catch(Exception e){
			throw new InvalidInput();
		}
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable String userId){
		try{
			this.userService.deleteUser(Long.parseLong(userId));
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


}
