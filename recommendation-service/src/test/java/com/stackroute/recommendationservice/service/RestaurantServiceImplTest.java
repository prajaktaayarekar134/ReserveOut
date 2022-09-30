package com.stackroute.recommendationservice.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.recommendationservice.model.RestaurantEntity;
import com.stackroute.recommendationservice.repository.RestaurantRepo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RestaurantServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RestaurantServiceImplTest {
    @MockBean
    private RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantServiceImpl restaurantServiceImpl;


    @Test
    void testListRestaurant() {
        ArrayList<RestaurantEntity> restaurantEntityList = new ArrayList<>();
        when(restaurantRepo.getRestaurant((String) any())).thenReturn(restaurantEntityList);
        List<RestaurantEntity> actualListRestaurantResult = restaurantServiceImpl.listRestaurant("Query");
        assertSame(restaurantEntityList, actualListRestaurantResult);
        assertTrue(actualListRestaurantResult.isEmpty());
        verify(restaurantRepo).getRestaurant((String) any());
    }


//    @Test
//    void testAddRestaurant() {
//        RestaurantEntity restaurantEntity = new RestaurantEntity();
//        restaurantEntity.setRestaurantId("110");
//        restaurantEntity.setRestaurantName("Punjab Dhaba");
//        restaurantEntity.setRestaurantType("Non Veg");
//        restaurantEntity.setRestaurantCuisine("North Indian");
//        restaurantEntity.setRestaurantAddress("Kolkata");
//        restaurantEntity.setTableBookingPrice(100);
//        restaurantEntity.setRestaurantContact("7278947384");
//        restaurantEntity.setRestaurantApi("localhost:8080/api/restaurant/110");
//        when(restaurantRepo.save((RestaurantEntity) any())).thenReturn(restaurantEntity);
//
//        RestaurantEntity restaurantEntity1 = new RestaurantEntity();
//        restaurantEntity1.setRestaurantId("111");
//        restaurantEntity1.setRestaurantName("Local Kitchen");
//        restaurantEntity1.setRestaurantType("Veg");
//        restaurantEntity1.setRestaurantCuisine("North Indian");
//        restaurantEntity1.setRestaurantAddress("Delhi");
//        restaurantEntity1.setTableBookingPrice(150);
//        restaurantEntity1.setRestaurantContact("6878947384");
//        restaurantEntity1.setRestaurantApi("localhost:8080/api/restaurant/111");
//        assertSame(restaurantEntity, restaurantServiceImpl.addRestaurant(restaurantEntity1));
//        verify(restaurantRepo).save((RestaurantEntity) any());
//    }


//    @Test
//    void testListRestaurentByRestaurantId() {
//        ArrayList<RestaurantEntity> restaurantEntityList = new ArrayList<>();
//        when(restaurantRepo.getRestaurantByRestaurantId((String) any())).thenReturn(restaurantEntityList);
//        List<RestaurantEntity> actualListRestaurentByRestaurantIdResult = restaurantServiceImpl
//                .listRestaurentByRestaurantId("111");
//        assertSame(restaurantEntityList, actualListRestaurentByRestaurantIdResult);
//        assertTrue(actualListRestaurentByRestaurantIdResult.isEmpty());
//        verify(restaurantRepo).getRestaurantByRestaurantId((String) any());
//    }
}

