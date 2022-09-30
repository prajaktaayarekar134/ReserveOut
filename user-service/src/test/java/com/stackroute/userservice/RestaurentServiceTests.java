package com.stackroute.userservice;

import com.stackroute.userservice.dao.RestaurantDao;
import com.stackroute.userservice.model.Restaurant;
import com.stackroute.userservice.service.RestaurantServiceImpl;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes=RestaurentServiceTests.class)
        public class RestaurentServiceTests {

        @Mock
        RestaurantDao restaurantDao;

        @InjectMocks
        RestaurantServiceImpl restaurantService;


        @Test
        @Order(1)
        public void test_getWishlist(){
                List<Restaurant> myrestaurant =new ArrayList<Restaurant>();

                myrestaurant.add(new Restaurant("1","Spice World","Bhiwani","123456"));

                when(restaurantDao.findAll()).thenReturn(myrestaurant);
                assertEquals(1,restaurantService.getRestaurant().size());
        }

        @Test
        @Order(2)
        public void test_addRestaurant() {
                Restaurant restaurant = new Restaurant("2", "Sneha", "Bhiwani", "7569842");
                when(restaurantDao.save(restaurant)).thenReturn(restaurant);
                assertEquals(restaurant, restaurantService.addRestaurant(restaurant));
        }
}

