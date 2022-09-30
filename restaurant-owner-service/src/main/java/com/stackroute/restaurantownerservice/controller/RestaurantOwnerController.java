package com.stackroute.restaurantownerservice.controller;

import com.stackroute.restaurantownerservice.config.MessageConfiguration;
import com.stackroute.restaurantownerservice.dto.OwnerDTO;
import com.stackroute.restaurantownerservice.exception.InvalidInput;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFound;
import com.stackroute.restaurantownerservice.model.Restaurant;
import com.stackroute.restaurantownerservice.repository.RestaurantOwnerRepository;
import com.stackroute.restaurantownerservice.service.OwnerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantOwnerController {
    @Autowired
    private OwnerService ownerService;

    //Update rabbitMq
    @Autowired
    private RabbitTemplate template;


    @ApiOperation("This Api will give all the Restaurant Details.")
    @GetMapping("/AllDetails")
    public List<Restaurant> viewAll() {
        try {
            return ownerService.viewAll();
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @ApiOperation("This Api will add the Restaurant Details.")
    @PostMapping
    public Restaurant save(@RequestBody Restaurant restaurant) {
        try {
            return ownerService.save(restaurant);
        } catch (Exception e) {
            throw new InvalidInput();
        }
    }

    @ApiOperation("This Api will update the Restaurant Details.")
    @PutMapping("/update")
    public Restaurant update(@RequestBody Restaurant restaurant) {
        try {
            return ownerService.update(restaurant);
        } catch (Exception e) {
            throw new InvalidInput();
        }
    }

    @ApiOperation("This Api will give the restaurant based on a name.")
    @GetMapping("/findByName/{name}")
    public ResponseEntity<?> findByRestaurantName(@PathVariable("name") String name) throws RestaurantNotFound {
        try {
            return new ResponseEntity<>(ownerService.findByRestaurantName(name), HttpStatus.OK);
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @ApiOperation("This Api will give the restaurant based on its type.")
    @GetMapping("/findByType/{type}")
    public List<Restaurant> findByRestaurantType(@PathVariable("type") String type) {
        try {
            return ownerService.findByRestaurantType(type);
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @ApiOperation("This Api will give the restaurant based on Id")
    @GetMapping("/findById/{id}")
    public Restaurant findById(@PathVariable("id") String id) {
        try {
            return ownerService.findById(id);
        } catch (Exception e) {
            throw new RestaurantNotFound();
        }
    }

    @ApiOperation("This Api will delete the restaurant based on Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            ownerService.delete(id);
            return new ResponseEntity<>("Deleted Succesfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Restaurant not Found", HttpStatus.BAD_REQUEST);

        }

    }


}