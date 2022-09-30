package com.stackroute.restaurantownerservice.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @RabbitListener(queues = "owner_queue")
    public void consumeMessage() {

    }
}
