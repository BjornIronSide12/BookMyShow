package com.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    @Enumerated(EnumType.ORDINAL) // 1st value of the enum is 1, then second and so on
    private BookingStatus bookingStatus;
    // in 1 booking we can book many show-seats,
    // 1 show-seats can be booked by many bookings (considering refund)
    @ManyToMany
    private List<ShowSeat> showSeats;
    // 1 booking by 1 user
    // 1 user can do many booking
    @ManyToOne
    private User user;
    private LocalDateTime bookedAt;
    // 1 booking for 1 show
    // 1 show can have many bookings
    @ManyToOne
    private Show show;
    private double amount;
    // 1 booking can have many payments (partial payment -> discounts, reward points, failed payments as well
    // 1 payment can be done for 1 booking
    @OneToMany
    private List<Payment> payments;
}
