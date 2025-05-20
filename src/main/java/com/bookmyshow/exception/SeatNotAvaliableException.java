package com.bookmyshow.exception;

public class SeatNotAvaliableException extends RuntimeException{
    public SeatNotAvaliableException() {
        super("Selected seats are no longer available");
    }
}
