package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.entity.TheaterPartner;
import com.sapient.onlineticket.services.TheaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TheaterController {

    @Autowired
    private TheaterServiceImpl theaterService;

    /**
     * Method Description - This method is to register all theaters.
     * @param theater
     * @return
     */
    @PostMapping("/theaters")
    public TheaterPartner registerTheater(@Valid @RequestBody TheaterPartner theater)
    {
        return theaterService.registerTheater(theater);
    }

    @GetMapping("/theaters")
    public List<TheaterPartner> getAllTheaters()
    {
        return theaterService.getAllTheaters();
    }

}
