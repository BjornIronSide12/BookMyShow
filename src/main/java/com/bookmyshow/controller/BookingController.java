package com.bookmyshow.controller;

import com.bookmyshow.dto.BookMovieRequestDto;
import com.bookmyshow.dto.BookMovieResponseDto;
import com.bookmyshow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired // -> automatically find an obj of the param given, create it and pass it here
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto request) {

        return null;
    }
}
