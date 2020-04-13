package ru.otus.highloadarch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("user_role")
@AllArgsConstructor
public class UserRole {
    @Column("role")
    private Long role;
}
