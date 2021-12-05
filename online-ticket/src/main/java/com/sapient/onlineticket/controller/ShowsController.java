package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.ResponseMessage.ErrorResponse;
import com.sapient.onlineticket.entity.Shows;
import com.sapient.onlineticket.services.ShowServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
public class ShowsController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ShowServiceImpl showService;

    @PostMapping("/shows")
    public Shows registerShow(@Valid @RequestBody Shows show)
    {
        return this.showService.saveShows(show);

    }
    @PutMapping("/updateshow")
    public Shows updateShow(@Valid @RequestBody Shows show)
    {
        return this.showService.updateShow(show);
    }

    @GetMapping("/getshow/{showId}")
    public Shows getShow(@PathVariable("showId") Long showId)
    {
        return this.showService.getShow(showId);
    }

    @GetMapping("/getAllShowsByMovieAndDate/{movieId}/{date}")
    public ResponseEntity<Object> getAllShowsByMovieAndDate(
             @PathVariable("movieId") Long movieId,
             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        try
        {
            JSONObject showsByMovieAndDate =  this.showService.getAllShowsByMovieAndDate(movieId,date);
            return new ResponseEntity<>(showsByMovieAndDate.toString(), HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + e.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + ex.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteshow/{showId}")
    public String deleteShow(@PathVariable("showId") Long showId)
    {
        this.showService.deleteShow(showId);
        return "Show is successfully deleted";

    }




}
