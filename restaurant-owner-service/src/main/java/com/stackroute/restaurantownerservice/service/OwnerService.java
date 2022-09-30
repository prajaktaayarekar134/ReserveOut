package com.stackroute.restaurantownerservice.service;

import com.stackroute.restaurantownerservice.dto.OwnerDTO;
import com.stackroute.restaurantownerservice.model.Restaurant;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OwnerService {

    Restaurant update(Restaurant restaurant);

    List<Restaurant> viewAll();
    Restaurant save(Restaurant restaurant);

    List<Restaurant> findByRestaurantName(String name);
    List<Restaurant> findByRestaurantType(String type);
    Restaurant findById(String id);


    void delete(String id);
}
