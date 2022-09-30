package com.stackroute.userservice.service;

import com.stackroute.userservice.dao.RestaurantDao;
import com.stackroute.userservice.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public List<Restaurant> getRestaurant() {
        return restaurantDao.findAll();
    }


    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantDao.save(restaurant);
        return restaurant;
    }
}
