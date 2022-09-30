package com.stackroute.restaurantownerservice.service;

import com.stackroute.restaurantownerservice.config.MessageConfiguration;
import com.stackroute.restaurantownerservice.config.Producer;
import com.stackroute.restaurantownerservice.dto.OwnerDTO;
import com.stackroute.restaurantownerservice.exception.InvalidInput;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFound;
import com.stackroute.restaurantownerservice.model.Restaurant;
import com.stackroute.restaurantownerservice.repository.RestaurantOwnerRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private Producer producer;

    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    @Autowired
    private RabbitTemplate template;


    @Override
    public Restaurant save(Restaurant restaurant) {
        try {
            return restaurantOwnerRepository.save(restaurant);
        } catch (Exception e) {
            throw new InvalidInput();
        }
    }


    @Override
    public Restaurant update(Restaurant restaurant) {

        String restaurantId = restaurant.getRestaurantId();
        Restaurant restaurant1 = restaurantOwnerRepository.findAll().stream().filter(r -> r.getRestaurantId().equals(restaurantId)).findAny().get();
        restaurant1.setRestaurantName(restaurant.getRestaurantName());
        restaurant1.setRestaurantType(restaurant.getRestaurantType());
        restaurant1.setNoOfTable(restaurant.getNoOfTable());
        restaurant1.setRestaurantAddress(restaurant.getRestaurantAddress());
        restaurant1.setRestaurantCuisine(restaurant.getRestaurantCuisine());
        restaurant1.setTableBookingPrice(restaurant.getTableBookingPrice());
        restaurant1.setOwnerName(restaurant.getOwnerName());
        restaurant1.setEmail(restaurant.getEmail());
        restaurant1.setRestaurantTimings(restaurant.getRestaurantTimings());
        restaurant1.setRestaurantContact(restaurant.getRestaurantContact());
        restaurant1.setRestaurantApi(restaurant.getRestaurantApi());
        try {
            return restaurantOwnerRepository.save(restaurant1);
        } catch (Exception e) {
            throw new InvalidInput();
        }
    }

    @Override
    public List<Restaurant> viewAll() {
        try {
            return restaurantOwnerRepository.findAll();
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @Override
    public List<Restaurant> findByRestaurantName(String name) {
        try {

            List<Restaurant> restaurants = restaurantOwnerRepository.findByRestaurantName(name);
            if (restaurants.isEmpty()) {
                throw new RestaurantNotFound();
            } else {
                return restaurantOwnerRepository.findByRestaurantName(name);
            }
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @Override
    public List<Restaurant> findByRestaurantType(String type) {
        try {
            return restaurantOwnerRepository.findByRestaurantType(type);
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @Override
    public Restaurant findById(String id) {
        Restaurant restaurant = restaurantOwnerRepository.findById(id).get();
        OwnerDTO dto = new OwnerDTO();

        dto.setRestaurantId(restaurant.getRestaurantId());
        dto.setRestaurantName(restaurant.getRestaurantName());
        dto.setRestaurantAddress(restaurant.getRestaurantAddress());
        dto.setRestaurantContact(restaurant.getRestaurantContact());

        template.convertAndSend(MessageConfiguration.EXCHANGE,
                MessageConfiguration.ROUTING_KEY, dto);


        return restaurantOwnerRepository.findById(id).get();
    }

    @Override
    public void delete(String id) {

        Optional<Restaurant> optional = restaurantOwnerRepository.findById(id);
        if (optional.isEmpty()) {
            throw new RestaurantNotFound();
        }
        restaurantOwnerRepository.deleteById(id);

    }
}


