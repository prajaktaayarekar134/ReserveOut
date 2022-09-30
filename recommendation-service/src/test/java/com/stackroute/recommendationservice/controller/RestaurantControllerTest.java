package com.stackroute.recommendationservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.exception.ResourceNotFound;
import com.stackroute.recommendationservice.model.RestaurantEntity;
import com.stackroute.recommendationservice.service.RestaurantService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

@ContextConfiguration(classes = {RestaurantController.class})
@ExtendWith(SpringExtension.class)
class RestaurantControllerTest {
    @Autowired
    private RestaurantController restaurantController;

    @MockBean
    private RestaurantService restaurantService;


//    @Test
//    void testAddRestaurant() throws Exception {
//        RestaurantEntity restaurantEntity = new RestaurantEntity();
//        restaurantEntity.setNoOfTable("4");
//        restaurantEntity.setOwnerName("Alex");
//        restaurantEntity.setRestaurantAddress("Kolkata");
//        restaurantEntity.setRestaurantApi("localhost:8080/api/restaurant/110");
//        restaurantEntity.setRestaurantContact("7296728890");
//        restaurantEntity.setRestaurantCuisine("North Indian");
//        restaurantEntity.setRestaurantId("110");
//        restaurantEntity.setRestaurantName("Punjab Dhaba");
//        LocalDateTime atStartOfDayResult = LocalDateTime.ofEpochSecond(1L,0, ZoneOffset.ofHours(0));
//        restaurantEntity.setRestaurantTimings(atStartOfDayResult);
//        restaurantEntity.setRestaurantType("Non Veg");
//        restaurantEntity.setTableBookingPrice(100);
//        when(restaurantService.addRestaurant((RestaurantEntity) any())).thenReturn(restaurantEntity);
//
//        RestaurantEntity restaurantEntity1 = new RestaurantEntity();
//        restaurantEntity1.setNoOfTable("6");
//        restaurantEntity1.setOwnerName("Ana");
//        restaurantEntity1.setRestaurantAddress("Delhi");
//        restaurantEntity1.setRestaurantApi("localhost:8080/api/restaurant/111");
//        restaurantEntity1.setRestaurantContact("6724972567");
//        restaurantEntity1.setRestaurantCuisine("Asia");
//        restaurantEntity1.setRestaurantId("111");
//        restaurantEntity1.setRestaurantName("Local Highlight");
//        LocalDateTime atStartOfDayResult1 = LocalDateTime.ofEpochSecond(1L,0, ZoneOffset.ofHours(0));
//        restaurantEntity.setRestaurantTimings(atStartOfDayResult1);
//        restaurantEntity1.setRestaurantType("Veg");
//        restaurantEntity1.setTableBookingPrice(150);
//        String content = (new ObjectMapper()).writeValueAsString(restaurantEntity1);
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/add")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(content);
//        MockMvcBuilders.standaloneSetup(restaurantController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content()
//                        .string(
//                                "{\"restaurantId\":\"110\",\"restaurantName\":\"Punjab Dhaba\",\"restaurantType\":\"Non Veg\",\"restaurantAddress"
//                                        + "\":\"Kolkata\",\"noOfTable\":\"4\",\"ownerName\":\"Alex\",\"restaurantCuisine\":\"North"
//                                        + " Indian\",\"restaurantTimings\":[1970,1,1,0,0,1],\"tableBookingPrice\":100,\"restaurantContact\":\"7296728890\","
//                                        + "\"restaurantApi\":\"localhost:8080/api/restaurant/110\"}"));
//    }
//    @Test
//    void testGetRestaurantById() throws Exception {
//        when(restaurantService.listRestaurentByRestaurantId((String) any())).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/restaurant/{restaurantId}", "112");
//        MockMvcBuilders.standaloneSetup(restaurantController)
//                .build()
//                .perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }
//
//    @Test
//    void testGetRestaurantById2() throws Exception {
//        when(restaurantService.listRestaurentByRestaurantId((String) any()))
//                .thenThrow(new ResourceNotFound("Not found"));
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/restaurant/{restaurantId}", "116");
//        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(restaurantController)
//                .build()
//                .perform(requestBuilder);
//        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//
//    @Test
//    void testGetRestaurantById3() throws Exception {
//        when(restaurantService.listRestaurentByRestaurantId((String) any())).thenReturn(new ArrayList<>());
//        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/restaurant/{restaurantId}", "110");
//        getResult.characterEncoding("Encoding");
//        MockMvcBuilders.standaloneSetup(restaurantController)
//                .build()
//                .perform(getResult)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.content().string("[]"));
//    }


    @Test
    void testListOfRestaurants() throws Exception {
        when(restaurantService.listRestaurant((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/search").param("query", "Kolkata");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(restaurantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("? Restaurant not found !! You can search with Restaurant Name, Location, Cuisine"));
    }


    @Test
    void testListOfRestaurants2() throws Exception {
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setNoOfTable("?");
        restaurantEntity.setOwnerName("?");
        restaurantEntity.setRestaurantAddress("Delhi");
        restaurantEntity.setRestaurantApi("?");
        restaurantEntity.setRestaurantContact("?");
        restaurantEntity.setRestaurantCuisine("?");
        restaurantEntity.setRestaurantId("111");
        restaurantEntity.setRestaurantName("?");
        LocalDateTime atStartOfDayResult = LocalDateTime.ofEpochSecond(1L,0, ZoneOffset.ofHours(0));
        restaurantEntity.setRestaurantTimings(atStartOfDayResult);
        restaurantEntity.setRestaurantType("?");
        restaurantEntity.setTableBookingPrice(150);

        ArrayList<RestaurantEntity> restaurantEntityList = new ArrayList<>();
        restaurantEntityList.add(restaurantEntity);
        when(restaurantService.listRestaurant((String) any())).thenReturn(restaurantEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/search").param("query", "Del");
        MockMvcBuilders.standaloneSetup(restaurantController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"restaurantId\":\"111\",\"restaurantName\":\"?\",\"restaurantType\":\"?\",\"restaurantAddress\":\"Delhi\",\"noOfTable\":\"?\",\"ownerName\":\"?\",\"restaurantCuisine\":\"?\",\"restaurantTimings\":[1970,1,1,0,0,1],\"tableBookingPrice"
                                        + "\":150,\"restaurantContact\":\"?\",\"restaurantApi\":\"?\"}]"));
    }

    @Test
    void testListOfRestaurants3() throws Exception {
        when(restaurantService.listRestaurant((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/search").param("query", "");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(restaurantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("? Please Enter Something !!"));
    }


    @Test
    void testListOfRestaurants4() throws Exception {
        when(restaurantService.listRestaurant((String) any()))
                .thenThrow(new ResourceNotFound("Not found"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/search").param("query", "Nagpur");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(restaurantController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("? Restaurant not found !! You can search with Restaurant Name, Location, Cuisine"));
    }
}