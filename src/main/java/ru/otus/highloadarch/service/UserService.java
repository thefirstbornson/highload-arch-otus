package ru.otus.highloadarch.service;

import ru.otus.highloadarch.domain.User;

import java.util.List;

public interface UserService extends CrudService<User> {
    User findByEmail (String email);
    List<User> generateUsers (Long count);
    List<User> saveAll(List<User> users);

    List<User> findUsersUsingPattern(String firstNamePattern, String lastNamePattern);
}
