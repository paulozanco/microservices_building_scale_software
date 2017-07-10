package co.paulozan.book.uservice.core.domain;

public abstract class Entity<T> {

  protected T id;
  protected String name;

  public T getId() {
    return id;
  }

  public void setId(T id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
