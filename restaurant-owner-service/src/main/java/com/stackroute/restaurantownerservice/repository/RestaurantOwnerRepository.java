package com.stackroute.restaurantownerservice.repository;

import com.stackroute.restaurantownerservice.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantOwnerRepository extends MongoRepository<Restaurant,String> {
   List<Restaurant> findByRestaurantName(String name);
     List<Restaurant> findByRestaurantType(String type);
}
