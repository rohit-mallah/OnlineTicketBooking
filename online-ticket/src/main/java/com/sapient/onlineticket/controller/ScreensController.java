package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.entity.Screens;
import com.sapient.onlineticket.entity.Shows;
import com.sapient.onlineticket.repository.ShowRepository;
import com.sapient.onlineticket.services.ScreenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class ScreensController {

    @Autowired
    private ScreenServiceImpl screenService;

    @Autowired
    private ShowRepository showRepository;

    /**
     * Screen - we can say Show as well. Primary Key for Screens are TheaterId, MovieId, ScreenStartsAt (which is nothing but Start timing of the show)
     * Method Description - This Method is used to Register one screen/show.
     * @param screen
     * @return
     */
    @PostMapping("/screens")
    public String registerScreen(@RequestBody Screens screen) {
        screenService.registerScreens(screen);
        return "ScreensAdded";
    }


    @DeleteMapping("/screens/{theaterId}/{movieId}/{screenStartsAt}/{date}")
    public String deleteScreen(@PathVariable(value="theaterId") String theaterId,
                               @PathVariable(value="movieId") String movieId,
                               @PathVariable(value="screenStartsAt") String screenStartsAt,
                               @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        screenService.deleteScreens(theaterId,movieId,screenStartsAt,date);
        return "screen deleted";
    }
    /***
     * Method Description - to see all Screens availble.
     * @return
     */
    @GetMapping("/screens")
    public List<Screens> getAllScreens()
    {
        return screenService.getAllScreens();
    }
}
