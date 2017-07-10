package co.paulozan.book.restaurant.domain;

import co.paulozan.book.uservice.core.domain.BaseEntity;
import java.math.BigInteger;
import java.util.Arrays;

public class Table extends BaseEntity<BigInteger> {

  private int capacity;

  public Table(String name, BigInteger id, int capacity) {
    super(id, name);
    this.capacity = capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public int getCapacity() {
    return capacity;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("id: {}, name: {}, capacity: {}", this.getId(), this.getName(), this.getCapacity()));
    return sb.toString();
  }

}
