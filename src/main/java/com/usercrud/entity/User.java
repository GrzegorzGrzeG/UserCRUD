package com.usercrud.entity;

public class User {
    private long id;
    private String email;
    private String userName;
    private String password;

    public User() {
    }

    public User(long id, String email, String userName, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public User setIdUser(long id) {
        this.id = id;
        return this;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public User setEmailUser(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserNameUser(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public User setPasswordUser(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return id + "\t" + email + "\t" + userName + "\t" + password;
    }
}