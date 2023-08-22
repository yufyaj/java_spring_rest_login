package com.test.java_spring_rest_login.model;

public interface UserInterface {
    String userId = null;
    String password = null;

    public String getUserId();
    public void   setUserId(String userId);
    public String getPassword();
    public void   setPassword(String password);
}
