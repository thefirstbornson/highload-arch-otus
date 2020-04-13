package ru.otus.highloadarch.repository;

import java.util.List;

public interface GenericRepository<T> {
    T findById(long id);

    List<T> findAll();

    Long getCount();

    T save(T entity);

    T update(T object) ;

    void delete(T object);
}
