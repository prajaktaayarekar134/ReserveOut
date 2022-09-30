package com.stackroute.restaurantownerservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.restaurantownerservice.model.Restaurant;
import com.stackroute.restaurantownerservice.service.OwnerService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {RestaurantOwnerController.class})
@ExtendWith(SpringExtension.class)
class RestaurantOwnerControllerTest {
    @MockBean
    private OwnerService ownerService;

    @Autowired
    private RestaurantOwnerController restaurantOwnerController;


    @Test
    void testViewAll() throws Exception {
        when(ownerService.viewAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurant/AllDetails");
        MockMvcBuilders.standaloneSetup(restaurantOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testUpdate() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setNoOfTable(10);
        restaurant.setOwnerName("Owner Name");
        restaurant.setEmail("Email");
        restaurant.setRestaurantAddress("42 Main St");
        restaurant.setRestaurantApi("Restaurant Api");
        restaurant.setRestaurantContact("Restaurant Contact");
        restaurant.setRestaurantCuisine("Restaurant Cuisine");
        restaurant.setRestaurantId("42");
        restaurant.setRestaurantName("Restaurant Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        restaurant.setRestaurantTimings(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant.setRestaurantType("Restaurant Type");
        restaurant.setTableBookingPrice(1);
        when(ownerService.update((Restaurant) any())).thenReturn(restaurant);

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setNoOfTable(10);
        restaurant1.setOwnerName("Owner Name");
        restaurant1.setEmail("Email");
        restaurant1.setRestaurantAddress("42 Main St");
        restaurant1.setRestaurantApi("Restaurant Api");
        restaurant1.setRestaurantContact("Restaurant Contact");
        restaurant1.setRestaurantCuisine("Restaurant Cuisine");
        restaurant1.setRestaurantId("42");
        restaurant1.setRestaurantName("Restaurant Name");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        restaurant1.setRestaurantTimings(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant1.setRestaurantType("Restaurant Type");
        restaurant1.setTableBookingPrice(1);
        String content = (new ObjectMapper()).writeValueAsString(restaurant1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/restaurant/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(restaurantOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"restaurantId\":\"42\",\"restaurantName\":\"Restaurant Name\",\"restaurantType\":\"Restaurant Type\",\"noOfTable\":\"No"
                                        + " Of Table\",\"restaurantAddress\":\"42 Main St\",\"restaurantCuisine\":\"Restaurant Cuisine\",\"tableBookingPrice"
                                        + "\":1,\"ownerName\":\"Owner Name\",\"email\":\"Email\",\"restaurantTimings\":0,\"restaurantContact\":\"Restaurant Contact\",\"restaurantApi"
                                        + "\":\"Restaurant Api\"}"));
    }


    @Test
    void testFindByRestaurantName() throws Exception {
        when(ownerService.findByRestaurantName((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurant/findByName/{name}", "Name");
        MockMvcBuilders.standaloneSetup(restaurantOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindByRestaurantType() throws Exception {
        when(ownerService.findByRestaurantType((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurant/findByType/{type}",
                "Type");
        MockMvcBuilders.standaloneSetup(restaurantOwnerController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testSave() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setNoOfTable(10);
        restaurant.setOwnerName("Owner Name");
        restaurant.setRestaurantAddress("42 Main St");
        restaurant.setRestaurantApi("Restaurant Api");
        restaurant.setRestaurantContact("Restaurant Contact");
        restaurant.setRestaurantCuisine("Restaurant Cuisine");
        restaurant.setRestaurantId("42");
        restaurant.setRestaurantName("Restaurant Name");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        restaurant.setRestaurantTimings(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        restaurant.setRestaurantType("Restaurant Type");
        restaurant.setTableBookingPrice(1);
        String content = (new ObjectMapper()).writeValueAsString(restaurant);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(restaurantOwnerController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

