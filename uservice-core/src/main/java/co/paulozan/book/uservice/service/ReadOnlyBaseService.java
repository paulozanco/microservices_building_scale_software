package co.paulozan.book.uservice.service;

import co.paulozan.book.uservice.core.repository.Repository;

public class ReadOnlyBaseService<TE, T> {

  private Repository<TE, T> repository;

  ReadOnlyBaseService(Repository<TE, T> repository) {
    this.repository = repository;
  }
}
