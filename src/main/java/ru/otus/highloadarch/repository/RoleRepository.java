package ru.otus.highloadarch.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.highloadarch.domain.Role;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
