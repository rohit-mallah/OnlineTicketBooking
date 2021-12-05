package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.ResponseMessage.ErrorResponse;
import com.sapient.onlineticket.entity.Seats;
import com.sapient.onlineticket.services.SeatsServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SeatsController {


    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeatsServiceImpl seatsService;

    @PostMapping("/addseats")
    public ResponseEntity<Object> addSeats(@Valid @RequestBody List<Seats> seats)
    {
        try
        {
            this.seatsService.saveSeats(seats);
            return new ResponseEntity<>("Sucessfully Saved Seats", HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            this.LOGGER.error("Error in Add Seats() " + e.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            this.LOGGER.error("Error in Add Seats() " + ex.getMessage());
            return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
        }

    }
}
