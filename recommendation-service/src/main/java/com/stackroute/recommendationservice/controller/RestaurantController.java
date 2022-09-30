package com.stackroute.recommendationservice.controller;


import com.stackroute.recommendationservice.exception.ResourceNotFound;
import com.stackroute.recommendationservice.model.RestaurantEntity;
import com.stackroute.recommendationservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantServiceImpl;




    @GetMapping("/search")
    public ResponseEntity<?> listOfRestaurants(@RequestParam("query") String query) {
        if (query.equals("")) {
            return new ResponseEntity<>("\ud83d\ude44"+" Please Enter Something !!", HttpStatus.NOT_FOUND);
        } else {
            try {
                if (restaurantServiceImpl.listRestaurant(query) != null && !restaurantServiceImpl.listRestaurant(query).isEmpty()) {
                    return ResponseEntity.ok(restaurantServiceImpl.listRestaurant(query));
                }
            } catch (ResourceNotFound e) {
                return new ResponseEntity<>("\ud83d\ude07"+" Restaurant not found !! You can search with Restaurant Name, Location, Cuisine", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("\ud83d\ude07"+" Restaurant not found !! You can search with Restaurant Name, Location, Cuisine", HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/selectRestaurantForBook/{id}")
    public RestaurantEntity findById(@PathVariable("id") String id){
        return restaurantServiceImpl.findById(id);
    }







}