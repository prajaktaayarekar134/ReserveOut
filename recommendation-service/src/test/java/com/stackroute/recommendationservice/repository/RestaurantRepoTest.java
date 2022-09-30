package com.stackroute.recommendationservice.repository;

import com.stackroute.recommendationservice.model.RestaurantEntity;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.time.LocalDateTime;
import java.util.List;

@DataMongoTest
class RestaurantRepoTest {


    @Autowired
    private RestaurantRepo restaurantRepo;
    private RestaurantEntity restaurantEntity;

    @BeforeEach
    public void setUp() throws Exception {
        restaurantEntity = new RestaurantEntity();
        restaurantEntity.setRestaurantId("100");
        restaurantEntity.setRestaurantName("Local High");
        restaurantEntity.setRestaurantType("Non Veg");
        restaurantEntity.setRestaurantAddress("Kolkata");
        restaurantEntity.setNoOfTable("4");
        restaurantEntity.setOwnerName("Alex");
        restaurantEntity.setRestaurantCuisine("North Indian");
        restaurantEntity.setRestaurantTimings(LocalDateTime.now());
        restaurantEntity.setTableBookingPrice(100);
        restaurantEntity.setRestaurantContact("8360977728");
        restaurantEntity.setRestaurantApi("localhost:8080/api/restaurant/100");

        restaurantRepo.deleteById("100");
    }

    @AfterEach
    public void tearDown() throws Exception {
        restaurantRepo.deleteById("100");
    }

    @Test
    void findAllRestaurantTest() {
        restaurantRepo.insert(restaurantEntity);
        List<RestaurantEntity> fetchRestaurant = restaurantRepo.findAll();
        Assert.assertFalse(fetchRestaurant.isEmpty());
        Assert.assertNotSame(fetchRestaurant, null);
    }



    @Test
    void getRestaurantByRestaurantIdTest() {
        restaurantRepo.insert(restaurantEntity);
        RestaurantEntity fetchedRestaurant = restaurantRepo.findById("100").get();
        Assert.assertEquals(restaurantEntity.getRestaurantId(),fetchedRestaurant.getRestaurantId());

    }



}