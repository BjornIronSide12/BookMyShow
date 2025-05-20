package com.bookmyshow.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super("User Not Found with the id: " + userId);
    }
}
