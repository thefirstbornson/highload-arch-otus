package ru.otus.highloadarch.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.highloadarch.domain.User;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Repository
public class UserRepositoryJDBCImpl extends GenericRepositoryJDBCImpl<User> implements UserRepository {

    public UserRepositoryJDBCImpl(NamedParameterJdbcOperations jdbcOperations) {
        super("user", jdbcOperations);
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("firstN", user.getFirstName())
                .addValue("lastN", user.getLastName())
                .addValue("sex", user.getSex())
                .addValue("email", user.geteMail())
                .addValue("interests", user.getInterests());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(String.format("insert into %s (f_name, l_name, sex, email, interests) " +
                        "values (:firstN, :lastN, :sex, :email, :interests)",
                this.getTableName()), parameters, keyHolder, new String[]{"id"});
        user.setId(Objects.requireNonNull(keyHolder.getKey())
                .longValue());
        return user;
    }

    @Override
    public User update(User user) {
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstN", user.getFirstName())
                .addValue("lastN", user.getLastName())
                .addValue("sex", user.getSex())
                .addValue("email", user.geteMail())
                .addValue("interests", user.getInterests());
        jdbcOperations.update(String.format("update %s set f_name=:firstN, l_name=:lastN, sex=:sex, email=:email, interests:=interests" +
                        " where id =:id", this.getTableName())
                , parameters);
        return user;
    }

    @Override
    public void delete(User user) {
        Map<String, Object> params = Collections.singletonMap("id", user.getId());
        jdbcOperations.update(String.format("delete from %s where id = :id", this.getTableName()), params);
    }

    @Override
    public RowMapper<User> getRowMapper() {
        return (ResultSet result, int rowNum) -> {
            User user = new User();
            user.setId(result.getLong("id"));
            user.setFirstName(result.getString("f_name"));
            user.setLastName(result.getString("l_name"));
            user.setSex(result.getString("sex"));
            user.seteMail(result.getString("email"));
            user.setInterests(result.getString("interests"));
            return user;
        };
    }

}
