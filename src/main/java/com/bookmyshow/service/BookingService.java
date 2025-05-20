package com.bookmyshow.service;

import com.bookmyshow.exception.SeatNotAvaliableException;
import com.bookmyshow.exception.ShowNotFoundException;
import com.bookmyshow.exception.UserNotFoundException;
import com.bookmyshow.models.*;
import com.bookmyshow.repository.BookingRepository;
import com.bookmyshow.repository.ShowRepository;
import com.bookmyshow.repository.ShowSeatRepository;
import com.bookmyshow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculator priceCalculator;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository,
                          ShowSeatRepository showSeatRepository, UserRepository userRepository,
                          PriceCalculator priceCalculator) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculator = priceCalculator;
    }

    //Transactional makes sure that the method operates within a single database transaction
    // if the method execute successfully then the changes are commited into DB
    // else if an exception occurs the transaction is rolled back, by reverting the changes

    // isolation levels are to isolate concurrent transactions
    // SERIALIZABLE is the highest level of isolation
    // each transaction is executed serially
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId) {
        // start transaction;
        // --- TODAY WE WILL START LOCK HERE
        // 1. Get user with that userId=
            Optional<User> userOptional = userRepository.findById(userId);
            if(userOptional.isEmpty()) {
                throw new UserNotFoundException(String.valueOf(userId));
            }

            User user = userOptional.get();
        // 2. Get show with that showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()) {
            throw new ShowNotFoundException(String.valueOf(showId));
        }

        Show bookedShow = showOptional.get();
        // -------------- TAKE LOCK ---------------
        // 3. Get ShowSeat with that seatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);


        // 4. Check if all show seats are available
        // 5. If no, throw error
        for(ShowSeat showSeat: showSeats) {
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVALIABLE)) {
                throw new SeatNotAvaliableException();
            }
        }

        List<ShowSeat> savedShowSeats = new ArrayList<>();

        // 6. If yes, Mark the status of show seats as LOCKED
        // 7. Save updated show seats to DB
        for(ShowSeat showSeat: showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            savedShowSeats.add(showSeatRepository.save(showSeat));
        }

        // ---------------END LOCK -----------------
        // 8. Create corresponding booking object));
        Booking booking = new Booking();
        booking.setBookedAt(LocalDateTime.now());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShow(bookedShow);
        booking.setUser(user);
        booking.setPayments(new ArrayList<>());
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeats, bookedShow));


        // 9. Return boking object
        // TODAY WE WILL END LOCK THERE
        Booking savedBooking = bookingRepository.save(booking);

        return savedBooking;
    }
}