package ru.otus.highloadarch.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import ru.otus.highloadarch.domain.Role;
import ru.otus.highloadarch.domain.User;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.Map;

public class RoleRepositoryImpl {

    private final String tableName;

    protected final NamedParameterJdbcOperations jdbcOperations;

    protected RoleRepositoryImpl(String tableName, NamedParameterJdbcOperations jdbcOperations) {
        this.tableName = tableName;
        this.jdbcOperations = jdbcOperations;
    }


//    @Override
//    public Role findByRole(String role) {
//        Map<String, Object> params = Collections.singletonMap("role", role);
//        return jdbcOperations.queryForObject(String.format("select * from %s where role = :role",getTableName())
//                , params, getRowMapper());
//    }

    public RowMapper<Role> getRowMapper() {
        return (ResultSet result, int rowNum) -> {
            Role role = new Role();
            role.setId(result.getLong("id"));
            role.setRole(result.getString("role"));
            return role;
        };
    }

    public String getTableName(){
        return tableName;
    }
}
