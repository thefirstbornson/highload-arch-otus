package ru.otus.highloadarch.read_repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.otus.highloadarch.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserReadRepository  {

    private final JdbcTemplate jdbc;

    @Autowired
    public UserReadRepository(@Qualifier("jdbcRead") JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<User> findById(Long id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from user u where u.id = ?", itemMapper, id));
    }

    public User findByEMail(String email) {
                return jdbc.queryForObject("select * from user u where u.email = ?", itemMapper, email);
    }

    public List<User> findByFirstNameLikeAndLastNameLike(String firstNamePattern, String lastNamePattern) {
        try (Connection connection = Objects.requireNonNull(jdbc.getDataSource())
                .getConnection()) {
            return jdbc.query("select * from user u where u.l_name like ? and u.f_name like ? order by u.id",
                    itemMapper, firstNamePattern, lastNamePattern);
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    private static final RowMapper<User> itemMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("f_name"));
        user.setLastName(rs.getString("l_name"));
        user.setSex(rs.getString("sex"));
        user.setEMail(rs.getString("email"));
        user.setInterests(rs.getString("interests"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        return user;
    };
}
