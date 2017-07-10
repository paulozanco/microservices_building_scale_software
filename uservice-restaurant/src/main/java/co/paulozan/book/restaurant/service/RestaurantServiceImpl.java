package co.paulozan.book.restaurant.service;

import co.paulozan.book.restaurant.domain.Restaurant;
import co.paulozan.book.restaurant.repository.RestaurantRepository;
import co.paulozan.book.uservice.core.domain.Entity;
import co.paulozan.book.uservice.service.BaseService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restaurantService")
public class RestaurantServiceImpl extends BaseService<Restaurant, String> implements
    RestaurantService {

  private RestaurantRepository<Restaurant, String> restaurantRepository;

  @Autowired
  public RestaurantServiceImpl(RestaurantRepository<Restaurant, String> restaurantRepository) {
    super(restaurantRepository);
    this.restaurantRepository = restaurantRepository;
  }

  public void add(Restaurant restaurant) throws Exception {
    if (restaurant.getName() == null || "".equals(restaurant.getName())) {
      throw new Exception("Restaurant name cannot be null or empty string.");
    }
    if (restaurantRepository.containsName(restaurant.getName())) {
      throw new Exception(
          String.format("There is already a product with the name - %s", restaurant.getName()));
    }
    super.add(restaurant);
  }

  @Override
  public Collection<Restaurant> findByName(String name) throws
      Exception {
    return restaurantRepository.findByName(name);
  }

  @Override
  public void update(Restaurant restaurant) throws Exception {
    restaurantRepository.update(restaurant);
  }

  @Override
  public void delete(String id) throws Exception {
    restaurantRepository.remove(id);
  }

  @Override
  public Entity findById(String restaurantId) throws Exception {
    return restaurantRepository.get(restaurantId);
  }

  @Override
  public Collection<Restaurant> findByCriteria(Map<String, ArrayList<String>> name)
      throws Exception {
    throw new UnsupportedOperationException("Not supported yet.");
//To change body of generated methods, choose Tools | Templates.
  }


}
