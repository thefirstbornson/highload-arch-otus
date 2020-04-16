package ru.otus.highloadarch.service;

import ru.otus.highloadarch.domain.User;

import java.util.List;
import java.util.Set;

public interface UserService extends CrudService<User> {
    User findByEmail (String email);
}
