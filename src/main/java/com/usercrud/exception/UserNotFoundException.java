package com.usercrud.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String ERROR_MSG = "User for given email/id %s not found";


    public UserNotFoundException(String email) {
        super(String.format(ERROR_MSG, email));
    }

    public UserNotFoundException(long id) {
        super(String.format(ERROR_MSG, id));
    }
}
