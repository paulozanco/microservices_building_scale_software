package com.packtpub.mmj.restaurant.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.packtpub.mmj.restaurant.domain.model.entity.Entity;
import com.packtpub.mmj.restaurant.domain.model.entity.Restaurant;
import com.packtpub.mmj.restaurant.domain.service.RestaurantService;
import com.packtpub.mmj.restaurant.domain.valueobject.RestaurantVO;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/restaurants")
public class RestaurantController {


    protected Logger logger = Logger.getLogger(RestaurantController.class.getName());


    protected RestaurantService restaurantService;


    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Autowired
    DiscoveryClient client;


    @RequestMapping("/")
    public List<String> home() {
        return client.getServices();
    }


    @HystrixCommand(fallbackMethod = "defaultRestaurants")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam("name") String name) {
        logger.info(String.format("restaurant-service findByName() invoked:{} for {} ", restaurantService.getClass().getName(), name));
        name = name.trim().toLowerCase();
        Collection<Restaurant> restaurants;
        try {
            restaurants = restaurantService.findByName(name);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception raised findByName REST Call", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurants.size() > 0 ? new ResponseEntity<>(restaurants, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @HystrixCommand(fallbackMethod = "defaultRestaurant")
    @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET)
    public ResponseEntity<Entity> findById(@PathVariable("restaurant_id") String id) {
        logger.info(String.format("restaurant-service findById() invoked:{} for {} ", restaurantService.getClass().getName(), id));
        id = id.trim();
        Entity restaurant;
        try {
            restaurant = restaurantService.findById(id);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised findById REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return restaurant != null ? new ResponseEntity<>(restaurant, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Restaurant> add(@RequestBody RestaurantVO restaurantVO) {
        logger.info(String.format("restaurant-service add() invoked: %s for %s", restaurantService.getClass().getName(), restaurantVO.getName()));
        System.out.println(restaurantVO);
        Restaurant restaurant = new Restaurant(null, null, null, null);
        BeanUtils.copyProperties(restaurantVO, restaurant);
        try {
            restaurantService.add(restaurant);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Exception raised add Restaurant REST Call {0}", ex);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<Entity> defaultRestaurant(String input) {
        logger.warning("Fallback method for restaurant-service is being used.");
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<Collection<Restaurant>> defaultRestaurants(String input) {
        logger.warning("Fallback method for user-service is being used.");
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
