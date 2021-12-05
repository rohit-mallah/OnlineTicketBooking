package com.sapient.onlineticket.services;

import com.sapient.onlineticket.entity.Seats;
import com.sapient.onlineticket.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatsServiceImpl {

    @Autowired
    private SeatsRepository seatsRepository;

    public void saveSeats(List<Seats> seats) {
        this.seatsRepository.saveAll(seats);

    }
}
