package com.sapient.onlineticket.services;

import com.sapient.onlineticket.entity.TheaterPartner;
import com.sapient.onlineticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl {

    @Autowired
    private TheaterRepository theaterRepository;
    public TheaterPartner registerTheater(TheaterPartner theater) {
        return theaterRepository.save(theater);
    }

    public List<TheaterPartner> getAllTheaters() {
        return theaterRepository.findAll();
    }
}
