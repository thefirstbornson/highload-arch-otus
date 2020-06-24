package ru.otus.highloadarch.service;

import ru.otus.highloadarch.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> readRepFindById(long id);

    User findByEmail (String email);

    User readRepfindByEmail(String email);

    List<User> generateUsers (Long count);
    List<User> saveAll(List<User> users);

    List<User> findUsersUsingPattern(String firstNamePattern, String lastNamePattern);

    List<User> readRepfindUsersUsingPattern(String firstNamePattern, String lastNamePattern);
}
