package com.sapient.onlineticket.controller;

import com.sapient.onlineticket.ResponseMessage.ErrorResponse;
import com.sapient.onlineticket.entity.User;
import com.sapient.onlineticket.repository.UserRepository;
import com.sapient.onlineticket.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/saveUser")
    public ResponseEntity<Object> saveUser(@Valid @RequestBody User user)
    {
            try
            {
                User user1 = this.userService.saveUser(user);
                return new ResponseEntity<>("Sucessfully User Saved --" + user1.getFirstName(), HttpStatus.OK);
            }catch (IllegalArgumentException e) {
                this.LOGGER.error("Error in Save User() " + e.getMessage());
                return new ResponseEntity<>(ErrorResponse.builder().errorMessage(e.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(), HttpStatus.BAD_REQUEST);

            } catch (Exception ex) {
                this.LOGGER.error("Error in Save User()  " + ex.getMessage());
                return new ResponseEntity<>(ErrorResponse.builder().errorMessage(ex.getMessage()).statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build(),HttpStatus.BAD_REQUEST);
            }


    }
}
