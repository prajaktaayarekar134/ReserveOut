package com.stackroute.userservice.service;

import com.stackroute.userservice.model.Restaurant;


import java.util.List;

public interface RestaurantService {
     List<Restaurant> getRestaurant();

    Restaurant addRestaurant(Restaurant restaurant);
}
