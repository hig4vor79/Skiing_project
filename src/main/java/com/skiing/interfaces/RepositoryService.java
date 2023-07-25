package com.skiing.interfaces;

import java.util.List;

public interface RepositoryService<T, ID> {
    T save(T entity);
    List<T> save(Iterable<T> entities);
    List<T> findAll();
    T getById(ID id);
}
