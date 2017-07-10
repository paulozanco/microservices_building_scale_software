package co.paulozan.book.uservice.service;

import co.paulozan.book.uservice.core.repository.Repository;
import java.util.Collection;

public class BaseService<TE, T> extends ReadOnlyBaseService<TE, T> {

  private Repository<TE, T> _repository;

  public BaseService(Repository<TE, T> repository) {
    super(repository);
    _repository = repository;
  }

  public void add(TE entity) throws Exception {
    _repository.add(entity);
  }

  public Collection<TE> getAll() {
    return _repository.getAll();
  }

}
