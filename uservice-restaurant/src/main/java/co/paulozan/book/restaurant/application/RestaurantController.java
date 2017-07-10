package co.paulozan.book.restaurant.application;


import co.paulozan.book.restaurant.domain.Restaurant;
import co.paulozan.book.restaurant.service.RestaurantService;
import co.paulozan.book.restaurant.valueobject.RestaurantVO;
import co.paulozan.book.uservice.core.domain.Entity;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

  protected static final Logger logger = Logger.getLogger(RestaurantController.class.getName());

  protected RestaurantService restaurantService;

  @Autowired
  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Restaurant>> findByName(@RequestParam("name") String name) {
    logger.info(String.format("restaurant-service findByName() invoked:{} for {} ",
        restaurantService.getClass().getName(), name));
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

  @RequestMapping(value = "/{restaurant_id}", method = RequestMethod.GET)
  public ResponseEntity<Entity> findById(@PathVariable("restaurant_id") String id) {
    logger.info(String.format("restaurant-service findById() invoked:{} for {} ",
        restaurantService.getClass().getName(), id));
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
    logger.info(String.format("restaurant-service add() invoked: %s for %s",
        restaurantService.getClass().getName(), restaurantVO.getName()));
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
}


