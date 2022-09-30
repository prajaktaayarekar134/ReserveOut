package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.Config.MQConfig;
import com.stackroute.recommendationservice.model.RestaurantEntity;
import com.stackroute.recommendationservice.model.RestaurantMessage;
import com.stackroute.recommendationservice.repository.RestaurantRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;


    @Autowired
    private RabbitTemplate template;



    @Override
    public List<RestaurantEntity> listRestaurant(String query) {

        return restaurantRepo.getRestaurant(query);
    }







    @Override
    public RestaurantEntity findById(String id) {
        RestaurantEntity restaurant=restaurantRepo.findById(id).get();
        RestaurantMessage restaurantMessage = new RestaurantMessage();

        restaurantMessage.setRestaurantId(restaurant.getRestaurantId());
        restaurantMessage.setRestaurantName(restaurant.getRestaurantName());
        restaurantMessage.setTableBookingPrice(restaurant.getTableBookingPrice());


        template.convertAndSend(MQConfig.EXCHANGE,
                MQConfig.ROUTING_KEY, restaurantMessage);


        return restaurantRepo.findById(id).get();
    }




}
