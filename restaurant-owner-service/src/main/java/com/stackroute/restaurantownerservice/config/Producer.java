package com.stackroute.restaurantownerservice.config;

import com.stackroute.restaurantownerservice.dto.OwnerDTO;
import com.stackroute.restaurantownerservice.model.Restaurant;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component

public class Producer {


    @Autowired
    private RabbitTemplate template;


    @PostMapping("/publish")
    public String publishMessage(@RequestBody OwnerDTO msg) {

        Restaurant restaurant = new Restaurant();

        msg.setRestaurantId(restaurant.getRestaurantId());
        msg.setRestaurantName(restaurant.getRestaurantName());
        msg.setRestaurantAddress(restaurant.getRestaurantAddress());
        msg.setRestaurantContact(restaurant.getRestaurantContact());
        template.convertAndSend(MessageConfiguration.EXCHANGE,
                MessageConfiguration.ROUTING_KEY, msg);

        return "Message Published";

    }


}