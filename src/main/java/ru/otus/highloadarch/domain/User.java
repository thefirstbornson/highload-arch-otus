package ru.otus.highloadarch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Table("user")
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String sex;
    private String eMail;
    private String interests;
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

    public void addUserRole(Role role){
        this.roles.add(new UserRole(role.getId()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(eMail, user.eMail) &&
                Objects.equals(interests, user.interests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sex, eMail, interests);
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
                '}';
    }
}
