package com.usercrud.exception;

public class UserCannotBeCreated extends RuntimeException{
    private static final String ERROR_MSG = "USER email: %s Username: %s Password: %s cannot be created";

    public UserCannotBeCreated(String email, String userName, String password){
        super(String.format(ERROR_MSG, email,userName,password));
    }

}
