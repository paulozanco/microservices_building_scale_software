package co.paulozan.book.uservice.core.repository;

import co.paulozan.book.uservice.core.domain.Entity;
import java.util.Collection;

public interface ReadOnlyRepository<TE, T> {

  boolean contains(T id);

  Entity get(T id);

  Collection<TE> getAll();

}
