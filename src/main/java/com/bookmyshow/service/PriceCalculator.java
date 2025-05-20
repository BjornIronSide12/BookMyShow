package com.bookmyshow.service;

import com.bookmyshow.models.Show;
import com.bookmyshow.models.ShowSeat;
import com.bookmyshow.models.ShowSeatType;
import com.bookmyshow.repository.ShowSeatRepository;
import com.bookmyshow.repository.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculator(ShowSeatRepository showSeatRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public double calculatePrice(List<ShowSeat> seatList, Show show) {
        // 1. find all the types of seats in the show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        // 2. find the types matching with the seatlist input
        // 3. compute the price
        double amount = 0;
        for(ShowSeat showSeat: seatList) {
            for(ShowSeatType seatType: showSeatTypes) {
                if(showSeat.getSeat().getSeatType().equals(seatType.getSeatType())) {
                    amount += seatType.getPrice();
                    break;
                }
            }
        }
        return amount;
    }
}
