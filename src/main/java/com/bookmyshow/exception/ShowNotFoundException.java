package com.bookmyshow.exception;

public class ShowNotFoundException extends RuntimeException{

    public ShowNotFoundException(String showId) {
        super("Show not found with the Id: " + showId);
    }
}
