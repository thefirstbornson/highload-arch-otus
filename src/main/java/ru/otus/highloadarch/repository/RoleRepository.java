package ru.otus.highloadarch.repository;

import ru.otus.highloadarch.domain.Role;

public interface RoleRepository {
    Role findByRole(String role);
}
