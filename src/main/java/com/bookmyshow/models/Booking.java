package com.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Booking extends BaseModel{
    private BookingStatus bookingStatus;
    private List<ShowSeat> showSeats;
    private User user;
    private LocalDateTime bookedAt;
    private Show show;
    private double amount;
    private List<Payment> payments;
}
