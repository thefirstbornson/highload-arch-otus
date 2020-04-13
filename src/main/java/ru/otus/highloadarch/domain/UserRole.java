package ru.otus.highloadarch.domain;

import org.springframework.data.relational.core.mapping.Table;

@Table("user_role")
public class UserRole {
    private Long role;

    public UserRole(Long role) {
        this.role = role;
    }
}
