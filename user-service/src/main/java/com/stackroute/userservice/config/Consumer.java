package com.stackroute.userservice.config;

import com.stackroute.userservice.domain.UserDTO;
import com.stackroute.userservice.model.Restaurant;
import com.stackroute.userservice.service.RestaurantServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {


    @Autowired
    RestaurantServiceImpl restaurantService;

    @RabbitListener(queues = MessageConfiguration.QUEUE)
    public void listener(UserDTO userDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(userDto.getRestaurantId());
        restaurant.setRestaurantName(userDto.getRestaurantName());
        restaurant.setContact(userDto.getRestaurantContact());
        restaurant.setLocation(userDto.getRestaurantAddress());
        restaurantService.addRestaurant(restaurant);

    }


}
