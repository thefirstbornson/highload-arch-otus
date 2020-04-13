package ru.otus.highloadarch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    @NotEmpty(message = "*Пожалуйста введите имя")
    @Column("f_name")
    private String firstName;
    @NotEmpty(message = "*Пожалуйста введите фамилию")
    @Column("l_name")
    private String lastName;
    private String sex;
    @Email(message = "*Пожалуйста введите корректный Email")
    @Column("email")
    private String eMail;
    private String interests;
    @Column("password")
    @Length(min = 5, message = "*Ваш пароль должен содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста введите пароль")
    private String password;
    private Set<UserRole> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String sex, String eMail, String interests) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.eMail = eMail;
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", eMail='" + eMail + '\'' +
                ", interests='" + interests + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void addUserRole(Role role) {
        UserRole userRole = new UserRole(role.getId());
        this.roles.add(userRole);
    }
}
