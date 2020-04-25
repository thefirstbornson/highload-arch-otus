package ru.otus.highloadarch.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.highloadarch.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select * from User u where u.email = :email")
    User findByEMail (String email);
}
