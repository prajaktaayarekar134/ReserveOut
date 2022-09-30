package com.stackroute.recommendationservice.repository;


import com.stackroute.recommendationservice.model.RestaurantEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo extends MongoRepository<RestaurantEntity, String> {
    @Query(value = "{$or:[{restaurantAddress:{$regex:?0,$options:'i'}},{restaurantName:{$regex:?0,$options:'i'}},{restaurantCuisine:{$regex:?0,$options:'i'}}]}")
     List<RestaurantEntity> getRestaurant(String query);

    @Query(value = "{restaurantId : ?0}")
     List<RestaurantEntity> getRestaurantByRestaurantId(String restaurantId);
}