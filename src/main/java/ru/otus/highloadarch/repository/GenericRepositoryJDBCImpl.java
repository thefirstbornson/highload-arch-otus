package ru.otus.highloadarch.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericRepositoryJDBCImpl<T> implements GenericRepository<T> {
    private final String tableName;

    protected final NamedParameterJdbcOperations jdbcOperations;

    protected GenericRepositoryJDBCImpl(String tableName, NamedParameterJdbcOperations jdbcOperations) {
        this.tableName = tableName;
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public T findById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return jdbcOperations.queryForObject(String.format("select * from %s where id = :id",getTableName())
                , params, getRowMapper());
    }

    @Override
    public List<T> findAll() {
        return jdbcOperations.query(String.format("select * from %s",getTableName()),getRowMapper());
    }

    @Override
    public Long getCount() {
        return jdbcOperations.queryForObject(String.format("select count(*) from %s",getTableName())
                ,new HashMap<>(), Long.class);
    }

    @Override
    public abstract T save(T entity);

    @Override
    public abstract T update(T object) ;

    @Override
    public abstract void delete(T object);

    public abstract RowMapper<T> getRowMapper();

    public String getTableName(){
        return tableName;
    }



}