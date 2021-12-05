package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.ResponseMessage.ErrorResponse;
import com.sapient.onlineticket.entity.Seats;
import com.sapient.onlineticket.services.GenericService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class GenericController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ErrorResponse errorResponse;
    @Autowired
    private GenericService genericService;


    @GetMapping("/getShowsByCityAndMovie/{city}/{movieId}/{date}")
    public ResponseEntity<Object> getAllShowsByCityMovieAndDate
            (@PathVariable("city") String city,
             @PathVariable("movieId") Long movieId,
             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        try
        {
            JSONObject showsByCity =  this.genericService.getAllShowsByCityMovieAndDate(city, movieId,date);
            return new ResponseEntity<>(showsByCity.toString(), HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + e.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + ex.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getShowsByCityAndMovieName/{city}/{movieName}/{date}")
    public ResponseEntity<Object> getAllShowsByCityMovieAndDate
            (@PathVariable("city") String city,
             @PathVariable("movieName") String movieName,
             @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
        try
        {
            JSONObject showsByCity =  this.genericService.getAllShowsByCityMovieNameAndDate(city, movieName,date);
            return new ResponseEntity<>(showsByCity.toString(), HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + e.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            this.LOGGER.error("Error in getAllShowsByCityMovieAndDate() " + ex.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/bookseats/{mobileNumber}")
    public ResponseEntity<Object> bookSeats(@RequestBody List<Seats> seats, @PathVariable("mobileNumber") String mobileNumber)
    {
        try
        {
            JSONObject booking =  this.genericService.bookSeats(seats,mobileNumber);
            return new ResponseEntity<>(booking.toString(), HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            this.LOGGER.error("Error in bookSeats() " + e.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            this.LOGGER.error("Error in bookSeats() " + ex.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
        }
    }
}
