package com.test.java_spring_rest_login.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;



@Entity
@Table(name="users")
@Data
public class UserEntity implements UserInterface {
    @Id
    @Column(name="user_id")
    private String userId;

    @Column(name="password")
    private String password;
}
