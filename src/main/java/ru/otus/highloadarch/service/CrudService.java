package ru.otus.highloadarch.service;

import ru.otus.highloadarch.domain.User;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    T create(User user);
    T update(User user);
    List<T> findAll();
    Optional<T> findById(long id);

}
