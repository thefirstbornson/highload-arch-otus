package ru.otus.highloadarch.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import ru.otus.highloadarch.domain.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select * from user u where u.email = :email")
    User findByEMail (String email);

    @Query("select * from user u where u.l_name like :lastNamePattern and u.f_name like :firstNamePattern order by u.id")
    List<User> findByFirstNameLikeAndLastNameLike(String firstNamePattern, String lastNamePattern);
}
