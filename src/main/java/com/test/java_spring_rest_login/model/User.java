package com.test.java_spring_rest_login.model;

import lombok.Data;


@Data
public class User implements UserInterface {
    private String userId;
    private String password;
}
