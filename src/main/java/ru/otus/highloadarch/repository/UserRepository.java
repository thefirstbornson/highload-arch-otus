package ru.otus.highloadarch.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.highloadarch.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
