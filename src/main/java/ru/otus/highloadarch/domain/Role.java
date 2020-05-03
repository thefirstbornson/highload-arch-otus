package ru.otus.highloadarch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@Table("role")
public class Role {
    @Id
    private long id;
    @Column("role")
    private String role;

}
