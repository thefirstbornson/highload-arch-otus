package ru.otus.highloadarch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Table("user")
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
    private Integer age;

    @Email(message = "*Пожалуйста введите корректный Email")
    @Column("email")
    private String eMail;

    private String interests;
    @Column("password")
    @Length(min = 5, message = "*Ваш пароль должен содержать минимум 5 символов")
    @NotEmpty(message = "*Пожалуйста введите пароль")
    private String password;
    private List<UserRole> roles = new ArrayList<>();
    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                ", interests='" + interests + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public User(String firstName, String lastName, String sex, Integer age, String eMail, String interests,
              String password, List<UserRole> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.age = age;
        this.eMail = eMail;
        this.interests = interests;
        this.password = password;
        this.roles = roles;
    }

    public void addUserRole(Role role) {
        UserRole userRole = new UserRole(role.getId());
        this.roles.add(userRole);
    }
}
