package com.stackroute.restaurantownerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.restaurantownerservice.exception.InvalidInput;
import com.stackroute.restaurantownerservice.exception.RestaurantNotFound;
import com.stackroute.restaurantownerservice.model.Restaurant;
import com.stackroute.restaurantownerservice.repository.RestaurantOwnerRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OwnerServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OwnerServiceImplTest {
    @Autowired
    private OwnerServiceImpl ownerServiceImpl;

    @MockBean
    private RestaurantOwnerRepository restaurantOwnerRepository;


    @Test
    void testSave() {
        Restaurant restaurant = new Restaurant();
        restaurant.setNoOfTable(20);
        restaurant.setOwnerName("Simran");
        restaurant.setRestaurantAddress("Delhi 567436");
        restaurant.setRestaurantApi("localhost:8081/api/restaurantId/105");
        restaurant.setRestaurantContact("8697498325");
        restaurant.setRestaurantCuisine("North Indian");
        restaurant.setRestaurantId("105");
        restaurant.setRestaurantName("Retro Dhaba");
        LocalDateTime atStartOfDayResult = LocalDate.of(2022, 10, 02).atStartOfDay();
        restaurant.setRestaurantTimings(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant.setRestaurantType("Non Veg");
        restaurant.setTableBookingPrice(100);
        when(restaurantOwnerRepository.save((Restaurant) any())).thenReturn(restaurant);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setNoOfTable(18);
        restaurant1.setOwnerName("Tarun");
        restaurant1.setRestaurantAddress("Agra 562749");
        restaurant1.setRestaurantApi("localhost:8081/api/restaurantId/106");
        restaurant1.setRestaurantContact("9678426956");
        restaurant1.setRestaurantCuisine("South Indian");
        restaurant1.setRestaurantId("106");
        restaurant1.setRestaurantName("Omg Restaurant");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(2022, 05, 10).atStartOfDay();
        restaurant1.setRestaurantTimings(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant1.setRestaurantType("Veg");
        restaurant1.setTableBookingPrice(250);
        assertSame(restaurant, ownerServiceImpl.save(restaurant1));
        verify(restaurantOwnerRepository).save((Restaurant) any());
    }


    @Test
    void testSave2() {
        when(restaurantOwnerRepository.save((Restaurant) any())).thenThrow(new InvalidInput());

        Restaurant restaurant = new Restaurant();
        restaurant.setNoOfTable(20);
        restaurant.setOwnerName("Rajib");
        restaurant.setRestaurantAddress("New Delhi");
        restaurant.setRestaurantApi("localhost:8081/api/restaurantId/107");
        restaurant.setRestaurantContact("8674958796");
        restaurant.setRestaurantCuisine("Asia");
        restaurant.setRestaurantId("107");
        restaurant.setRestaurantName("Local highlight");
        LocalDateTime atStartOfDayResult = LocalDate.of(2022, 02, 01).atStartOfDay();
        restaurant.setRestaurantTimings(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant.setRestaurantType("New Veg");
        restaurant.setTableBookingPrice(150);
        assertThrows(InvalidInput.class, () -> ownerServiceImpl.save(restaurant));
        verify(restaurantOwnerRepository).save((Restaurant) any());
    }


    @Test
    void testViewAll() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantOwnerRepository.findAll()).thenReturn(restaurantList);
        List<Restaurant> actualViewAllResult = ownerServiceImpl.viewAll();
        assertSame(restaurantList, actualViewAllResult);
        assertTrue(actualViewAllResult.isEmpty());
        verify(restaurantOwnerRepository).findAll();
    }


    @Test
    void testViewAll2() {
        when(restaurantOwnerRepository.findAll()).thenThrow(new InvalidInput());
        assertThrows(RestaurantNotFound.class, () -> ownerServiceImpl.viewAll());
        verify(restaurantOwnerRepository).findAll();
    }


    @Test
    void testFindByRestaurantName() {
        when(restaurantOwnerRepository.findByRestaurantName((String) any())).thenReturn(new ArrayList<>());
        assertThrows(RestaurantNotFound.class, () -> ownerServiceImpl.findByRestaurantName("Retro Dhaba"));
        verify(restaurantOwnerRepository).findByRestaurantName((String) any());
    }


    @Test
    void testFindByRestaurantName2() {
        Restaurant restaurant = new Restaurant();
        restaurant.setNoOfTable(22);
        restaurant.setOwnerName("Arko");
        restaurant.setRestaurantAddress("Delhi");
        restaurant.setRestaurantApi("localhost:8081/api/restaurantId/110");
        restaurant.setRestaurantContact("6274958796");
        restaurant.setRestaurantCuisine("Asia");
        restaurant.setRestaurantId("110");
        restaurant.setRestaurantName("Khana");
        LocalDateTime atStartOfDayResult = LocalDate.of(2022, 04, 11).atStartOfDay();
        restaurant.setRestaurantTimings(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant.setRestaurantType("New Veg");
        restaurant.setTableBookingPrice(350);

        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant);
        when(restaurantOwnerRepository.findByRestaurantName((String) any())).thenReturn(restaurantList);
        List<Restaurant> actualFindByRestaurantNameResult = ownerServiceImpl.findByRestaurantName("Omg Restaurant");
        assertSame(restaurantList, actualFindByRestaurantNameResult);
        assertEquals(1, actualFindByRestaurantNameResult.size());
        verify(restaurantOwnerRepository, atLeast(1)).findByRestaurantName((String) any());
    }

    @Test
    void testFindByRestaurantName3() {
        when(restaurantOwnerRepository.findByRestaurantName((String) any())).thenThrow(new InvalidInput());
        assertThrows(RestaurantNotFound.class, () -> ownerServiceImpl.findByRestaurantName("Local highlight"));
        verify(restaurantOwnerRepository).findByRestaurantName((String) any());
    }


    @Test
    void testFindByRestaurantType() {
        ArrayList<Restaurant> restaurantList = new ArrayList<>();
        when(restaurantOwnerRepository.findByRestaurantType((String) any())).thenReturn(restaurantList);
        List<Restaurant> actualFindByRestaurantTypeResult = ownerServiceImpl.findByRestaurantType("Veg");
        assertSame(restaurantList, actualFindByRestaurantTypeResult);
        assertTrue(actualFindByRestaurantTypeResult.isEmpty());
        verify(restaurantOwnerRepository).findByRestaurantType((String) any());
    }


    @Test
    void testFindByRestaurantType2() {
        when(restaurantOwnerRepository.findByRestaurantType((String) any())).thenThrow(new InvalidInput());
        assertThrows(RestaurantNotFound.class, () -> ownerServiceImpl.findByRestaurantType("New Veg"));
        verify(restaurantOwnerRepository).findByRestaurantType((String) any());
    }
}

