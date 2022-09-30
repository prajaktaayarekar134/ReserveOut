package com.stackroute.recommendationservice.service;


import com.stackroute.recommendationservice.model.RestaurantEntity;

import java.util.List;

public interface RestaurantService {




     List<RestaurantEntity> listRestaurant(String query);



     RestaurantEntity findById(String id);
}
