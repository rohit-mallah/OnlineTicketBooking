package com.sapient.onlineticket.services;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.sapient.onlineticket.Util.OnlineBookingException;
import com.sapient.onlineticket.entity.User;
import com.sapient.onlineticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {

        Optional<User> user1 = this.userRepository.findById(user.getMobileNumber());
        if(user1.isPresent())
        {
            throw new OnlineBookingException("This user is already registered " + user.getFirstName() + " with Mobile Number" + user.getMobileNumber());
        }
        else
        {
            return this.userRepository.save(user);
        }

    }
}
