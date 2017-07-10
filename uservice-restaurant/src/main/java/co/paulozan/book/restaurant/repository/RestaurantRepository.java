package co.paulozan.book.restaurant.repository;

import co.paulozan.book.uservice.core.repository.Repository;
import java.util.Collection;

public interface RestaurantRepository<Restaurant, String> extends Repository<Restaurant, String> {

  boolean containsName(String name);

  Collection<Restaurant> findByName(String name) throws Exception;

}
